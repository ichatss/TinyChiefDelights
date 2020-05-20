package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNotFound;
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
    public void setReview(String text, int rate, Long id) {

        if (cookRepository.findByIdAndUserRole(id, "COOK") == null) {
            throw new RuntimeException("Нет повара с " + id + " id");
        }

        try {
            Review review = new Review();
            review.setReview(text);
            review.setRate(rate);
            review.setCook(cookRepository.findByIdAndUserRole(id, "COOK"));
            reviewRepository.save(review);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

    }

    // Заполнить карзину
    public void setBasket(List<Long> dishListId) {
        Basket basket = new Basket();

        List<Dish> dishList = new ArrayList<>();

        for (Long i : dishListId) {
            dishList.add(dishRepository.getById(i));
        }
        basket.setDishList(dishList);
        basketRepository.save(basket);
    }

    // Подсчет цены
    public double calculateCoast(Long basketId) {

        Basket basket = basketRepository.getById(basketId);

        double coast = 0;
        for (Dish i : basket.getDishList()) {
            coast = coast + i.getDishCost();
        }
        System.out.println(coast);
        return coast;
    }

    // Сделать Заказ
    public void makeOrder(String address, String phoneNumber, Long customerId,
                          Long cookId, Long basketId) {

        double coast = calculateCoast(basketId);
        Customer customer = customerRepository
                            .findByIdAndUserRole(customerId, User.ROLE_CUSTOMER);

        if (coast <= customer.getWallet()) {
            customer.setWallet(customer.getWallet() - coast);
        }else{
            throw new RuntimeException("Недостаточно средств");
        }

        try {
            Order order = new Order();
            order.setPhoneNumber(phoneNumber);
            order.setAddress(address);
            Date date = new Date();
            order.setDateOrder(date);
            order.setOrderStatus(true);
            order.setCustomer(customerRepository.findByIdAndUserRole(customerId, "CUSTOMER"));
            order.setCook(cookRepository.findByIdAndUserRole(cookId, "COOK"));
            order.setBasket(basketRepository.getById(basketId));
            orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
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