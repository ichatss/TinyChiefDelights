package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNotFound;
import com.tinychiefdelights.exceptions.MainNullPointer;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CustomerService extends UserService {

    // Поля
    //
    // Injects in setters
    private CustomerRepository customerRepository;

    private CookRepository cookRepository;

    private UserRepository userRepository;

    private OrderRepository orderRepository;

    private DishRepository dishRepository;

    private ReviewRepository reviewRepository;

    private PasswordEncoder passwordEncoder;

    private BasketRepository basketRepository;


    // SETTERS
    //
    // Injects into Setters
    @Autowired
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Autowired
    public void setDishRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Autowired
    public void setCookRepository(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    // Методы
    //
    // Посмотреть меню
    public List<Dish> getMenu() {
        return dishRepository.findAll();
    }


    // Внести деньги на счет
    public void depositMoney(double money) {

        if (money <= 0) { // Делаем проверку на входной параметр (чтобы не была отрицательной)
            throw new MainIllegalArgument();
        }

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        customer.setWallet(customer.getWallet() + money);
        customerRepository.save(customer);
    }


    // Вывести деньги со счета
    public void withdrawMoney(double money) {

        if (money <= 0) { // Делаем проверку на входной параметр (чтобы не была отрицательной)
            throw new MainIllegalArgument();
        }

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        if (money <= customer.getWallet()) { // Делаем проверку, чтобы сумма указанная заказчиком была меньше кошелька
            customer.setWallet(customer.getWallet() - money);
            customerRepository.save(customer);
        } else {
            throw new MainIllegalArgument("На счете недостаточно средств!");
        }
    }


    // Оставить Отзыв
    public void setReview(Long id, byte rate, String rev) {

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        if (orderRepository
                .existsOrderByIdAndCustomerIdAndOrderStatusAndReviewIsNull(id, customer.getId(), false)) {

            try {

                Order order = orderRepository.getOrderByIdAndCustomerId(id, customer.getId());
                Review review = new Review();
                review.setReview(rev);
                review.setRate(rate);
                review.setOrder(order);
                reviewRepository.save(review);

                order.setReview(review);
                orderRepository.save(order);

                calculateRate(order.getCookList(), rate);

            } catch (NullPointerException ex) {
                throw new MainNotFound(id);
            }
        } else {
            throw new MainIllegalArgument("Заказ незавершен, либо отзыв уже оставлен!");
        }
    }


    // Заполнить карзину
    public void setBasket(List<Long> dishListId) {

        Basket basket = new Basket();

        List<Dish> dishList = new ArrayList<>();

        for (Long i : dishListId) {
            dishList.add(dishRepository.getById(i));

            // Делаем проверку, если блюда нет в меню
            if (!dishRepository.existsById(i)) {
                throw new MainNotFound(i);
            }
        }
        basket.setDishList(dishList);
        basketRepository.save(basket);
    }


    // Сделать Заказ
    public void makeOrder(String address, String phoneNumber, Long basketId, Date date, List<Long> cooksId) {

        basketRepository.findById(basketId).orElseThrow(() -> new MainNotFound(basketId));
        List<Cook> cooks = new ArrayList<>();
        double bonus = 200;

        if (cooksId != null) { // Здесь происходит выбор стратегии в зависимоси от того есть ли на входе назначенные повора
            for (Long i : cooksId) {
                cooks.add(cookRepository.getByIdAndUserRole(i, User.ROLE_COOK).orElseThrow(() -> new MainNotFound(i)));
            }
        }
        if (!cooksAreCorrect(cooks, basketId)) {
            throw new MainIllegalArgument("Не соответсвие типов поваров с типами блюд в корзине");
        } else {
            cooks = cooksAuto(basketId);
        }
        changeCookStatus(cooks); // запускаю метод для смены статуса поваров

        double cost = calculateCost(basketId, cooks, bonus);

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        withdrawMoney(cost); // вызываю метод снятия денег со счета

        Order order = new Order();
        order.setPhoneNumber(phoneNumber);
        order.setAddress(address);
        order.setDateOrder(date);
        order.setOrderStatus(true);
        order.setCustomer(customerRepository.findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER));
        order.setCookList(cooks);
        order.setBasket(basketRepository.getById(basketId));
        order.setCost(cost);
        orderRepository.save(order);
    }


    // Изменить свои данные
    public Customer editCustomer(String login, String name, String lastName) {

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        if (userRepository.getByLogin(login) == null) {
            customer.getUser().setLogin(login);
        } else {
            throw new MainIllegalArgument("Данный логин уже занят!");
        }
        customer.getUser().setName(name);
        customer.getUser().setLastName(lastName);
        return customerRepository.save(customer);
    }


    // Отменить заказ
    public void cancelOrder(Long id) {

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        if (orderRepository.existsOrderByIdAndCustomerIdAndOrderStatus(id, customer.getId(), true)) {
            Order order = orderRepository.getById(id);
            order.setOrderStatus(false);
            customer.setWallet(customer.getWallet() + order.getCost());
            orderRepository.save(order);
        } else {
            throw new MainNullPointer("Заказ с таким ID отсутствует!");
        }
    }


    // ТУТ НАХОДЯТСЯ ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ
    //
    //
    // Подсчет нового рейтинга повара
    private void calculateRate(List<Cook> cookList, float rate) {

        for (Cook c : cookList) {
            c.setRating((c.getRating() + rate) / 2);
            cookRepository.save(c);
        }
    }


    // Меняем статус назначенным поварам
    private void changeCookStatus(List<Cook> cooks) {
        for (Cook i : cooks) {
            i.setCookStatus(false);
            cookRepository.save(i);
        }
    }


    // Подсчет цены
    private double calculateCost(Long basketId, List<Cook> cooks, double bonus) {

        Basket basket = basketRepository.getById(basketId);

        double cost = 0;

        for (Dish i : basket.getDishList()) {
            cost += i.getDishCost();
        }

        for (Cook i : cooks) {
            cost += i.getStartSalary();

        }
        cost += bonus;

        return cost;
    }


    // Создание массива флагов для makeOrder
    public boolean[] generateFlags(Long basketId) {

        try {
            List<Dish> dishes = basketRepository.getById(basketId).getDishList();
            boolean[] flag = {false, false, false};

            for (Dish i : dishes) {
                if (i.getDishType() == DishType.CONFECTIONERY) {
                    flag[0] = true;
                }
                if (i.getDishType() == DishType.FISH) {
                    flag[1] = true;
                }
                if (i.getDishType() == DishType.MEAT) {
                    flag[2] = true;
                }
            }
            return flag;
        } catch (NullPointerException ex) {
            throw new MainNotFound(basketId);
        }
    }


    private boolean cooksAreCorrect(List<Cook> cooks, Long basketId) {
        boolean[] f1 = generateFlags(basketId);
        boolean[] f2 = {false, false, false};

        for (Cook i : cooks) {
            if (i.getCookType() == CookType.CONFECTIONER || i.getCookType() == CookType.CHEF) {
                f2[0] = true;
                continue;
            }
            if (i.getCookType() == CookType.FISH_COOK || i.getCookType() == CookType.CHEF) {
                f2[1] = true;
                continue;
            }
            if (i.getCookType() == CookType.MEAT_COOK || i.getCookType() == CookType.CHEF) {
                f2[2] = true;
                continue;
            }
        }
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            if (f1[i] != f2[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    // Автоматическое назначение поворов
    private List<Cook> cooksAuto(Long basketId) {

        boolean[] flag = generateFlags(basketId);
        List<Cook> cooks = new ArrayList<>();

        List<Cook> c;
        c = cookRepository.findByUserRoleAndCookStatus(User.ROLE_COOK, true);
        if (flag[0]) {
            for (Cook i : c) {
                if (i.getCookType() == CookType.CONFECTIONER || i.getCookType() == CookType.CHEF) {
                    cooks.add(i);
                    c.remove(i);
                    break;
                }
            }
        }
        if (flag[1]) {
            for (Cook i : c) {
                if (i.getCookType() == CookType.FISH_COOK || i.getCookType() == CookType.CHEF) {
                    cooks.add(i);
                    c.remove(i);
                    break;
                }
            }
        }
        if (flag[2]) {
            for (Cook i : c) {
                if (i.getCookType() == CookType.MEAT_COOK || i.getCookType() == CookType.CHEF) {
                    cooks.add(i);
                    c.remove(i);
                    break;
                }
            }
        }
        return cooks;
    }
}