package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNullPointer;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        customer.setWallet(customer.getWallet() + money);
        customerRepository.save(customer);

        if (money <= 0) { // Делаем проверку на входной параметр (чтобы не была отрицательной)
            throw new MainIllegalArgument();
        }
    }


    // Вывести деньги со счета
    public void withdrawMoney(double money) {

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        if (money <= customer.getWallet()) { // Делаем проверку, чтобы сумма указанная заказчиком была меньше кошелька
            customer.setWallet(customer.getWallet() - money);
            customerRepository.save(customer);
        } else {
            throw new MainIllegalArgument("Введенная Вами сумма превшает остаток на счете!");
        }

        if (money <= 0) { // Делаем проверку на входной параметр (чтобы не была отрицательной)
            throw new MainIllegalArgument();
        }
    }


    // Оставить Отзыв
    public void setReview(Long id, byte rate, String review) {

        try {

            Customer customer = customerRepository
                    .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);
            // Беру блюдо если и у заказчика оно имеется
            Order order = orderRepository.getOrderByIdAndCustomerId(id, customer.getId());
            // Позволяем оставлять отзыв если только отзыва еще нет и заказ завершен
            if (order.getReview() == null && !order.isOrderStatus()) {

                Review rev = new Review();
                rev.setReview(review);
                rev.setRate(rate);

                order.setReview(rev);

                reviewRepository.save(rev);

                orderRepository.save(order);

                calculateCookRate(order.getCookList(), id);
            } else {
                throw new MainIllegalArgument("Заказ еще не завершен или отзыв уже оставлен!");
            }
        } catch (NullPointerException ex) {
            throw new MainNullPointer("Заказ с ИД: " + id + " не найден!");
        }
    }


    private void calculateCookRate(List<Cook> cookList, Long orderId) {

        List<Cook> cooks = new ArrayList<>(cookList);

        Review review = reviewRepository.findReviewByOrderId(orderId);

        byte rate = review.getRate();

        for (Cook k :
                cooks) {
            k.setRating(k.getRating() + rate / 2);
        }
    }









    // Заполнить карзину
    public void setBasket(List<Long> dishListId) {

        Basket basket = new Basket();

        List<Dish> dishList = new ArrayList<>();

        for (Long i : dishListId) {
            dishList.add(dishRepository.getById(i));

            // Делаем проверку, если блюда нет в меню
            if (dishRepository.getById(i) == null) {
                throw new MainNullPointer("Блюдо ИД: " + i + " не найдено!");
            }
        }
        basket.setDishList(dishList);
        basketRepository.save(basket);
    }


    // Подсчет цены
    public double calculateCoast(Long basketId, List<Cook> cooks, double bonus) {

        Basket basket = basketRepository.getById(basketId);

        double coast = 0;
        for (Dish i : basket.getDishList()) {
            coast += i.getDishCost();
        }
        for (Cook i : cooks) {
            coast += i.getStartSalary();

        }

        coast += bonus;

        return coast;
    }

    // Создание массива флагов для makeOrder
    public boolean[] generateFlags(Long basketId) {

        List<Dish> dishes = basketRepository.getById(basketId).getDishList();

        boolean[] flag = new boolean[3];
        flag[0] = false;
        flag[1] = false;
        flag[2] = false;

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
    }


    // Автоматическое назначение поворов
    public List<Cook> cooksAuto(Long basketId) {

        boolean[] flag = generateFlags(basketId);

        List<Cook> cooks = new ArrayList<>();
        {
            List<Cook> c;
            c = cookRepository.findByUserRoleAndCookStatus(User.ROLE_COOK, true);
            if (flag[0]) {
                for (Cook i : c) {
                    if (i.getCookType() == CookType.CONFECTIONER) {
                        cooks.add(i);
                        c.remove(i);
                        break;
                    }
                }
            }
            if (flag[1]) {
                for (Cook i : c) {
                    if (i.getCookType() == CookType.FISH_COOK) {
                        cooks.add(i);
                        c.remove(i);
                        break;
                    }
                }
            }
            if (flag[2]) {
                for (Cook i : c) {
                    if (i.getCookType() == CookType.MEAT_COOK) {
                        cooks.add(i);
                        c.remove(i);
                        break;
                    }
                }
            }
        }
        return cooks;
    }


    //Получение списка свободных поворов
    public List<Cook> getFreeCooks() {
        List<Cook> freeCooks = cookRepository.findByUserRoleAndCookStatus(User.ROLE_COOK, true);
        return freeCooks;
    }

    public boolean cooksIsCorrect(List<Cook> cooks, Long basketId){

        boolean[] f1 = generateFlags(basketId);
        boolean[] f2 = {false, false, false};

        for (Cook i : cooks) {
            if(i.getCookType() == CookType.CONFECTIONER){
                f2[0] = true;
            }
            if(i.getCookType() == CookType.CONFECTIONER){
                f2[1] = true;
            }
            if(i.getCookType() == CookType.CONFECTIONER){
                f2[2] = true;
            }
        }

        return f1 == f2;
    }

    // Сделать Заказ
    public void makeOrder(String address, String phoneNumber, Long basketId, Date date, List<Long> cooksId) {

        List<Cook> cooks = new ArrayList<>();
        double bonus = 0;

        //сдесь происходит выбор стратегии в зависимоси от того есть ли на входе назначенные повора
        if (cooksId != null) {
            for (Long i : cooksId) {
                cooks.add(cookRepository.findByIdAndUserRole(i, User.ROLE_COOK));
                bonus = 200;
            }
        } else {
            cooks = cooksAuto(basketId);
        }

        if(!cooksIsCorrect(cooks, basketId)){
            throw new RuntimeException("Не соответсвие типов поваров с типами блюд в корзине");
        }

        //меняем статус, назначенным поварам
        for (Cook i : cooks) {
            i.setCookStatus(false);
            cookRepository.save(i);
        }

        double coast = calculateCoast(basketId, cooks, bonus);

        Customer customer = customerRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER);

        if (coast <= customer.getWallet()) {
            customer.setWallet(customer.getWallet() - coast);
        } else {
            throw new MainIllegalArgument("На счете недостаточно средств!");
        }

        Order order = new Order();
        order.setPhoneNumber(phoneNumber);
        order.setAddress(address);
        order.setDateOrder(date);
        order.setOrderStatus(true);
        order.setCustomer(customerRepository.findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_CUSTOMER));
        order.setCookList(cooks);
        order.setBasket(basketRepository.getById(basketId));
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

        try {

            Order order = orderRepository.getById(id);
            order.setOrderStatus(false);
            orderRepository.save(order);

        } catch (NullPointerException ex) {
            throw new MainNullPointer("Заказ с таким ID отсутствует!");
        }
    }


    // Регистрация
    public Customer registration(String name, String lastName, String login, String password) {

        User newUser = new User();

        newUser.setRole(User.ROLE_CUSTOMER);
        newUser.setName(name);
        newUser.setLastName(lastName);
        newUser.setLogin(login);
        newUser.setPassword(passwordEncoder.encode(password));
        Customer newCustomer = new Customer();
        newCustomer.setUser(newUser);
        newCustomer.setWallet(0);

        userRepository.save(newUser);

        return customerRepository.save(newCustomer);
    }



}