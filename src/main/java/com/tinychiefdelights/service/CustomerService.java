package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import org.aspectj.weaver.ast.Or;
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
    public void setBasketRepository(BasketRepository basketRepository){
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

    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    // Методы
    //
    // Внести деньги на счет
    public void depositMoney(Long id, double money) {
        Customer customer = customerRepository.findByIdAndUserRole(id, "CUSTOMER");
        try {
            customer.setWallet(customer.getWallet() + money);
            customerRepository.save(customer);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (NotFoundException e) {
            throw new NotFoundException(id);
        }

    }


    // Вывести деньги со счета
    public void withdrawMoney(Long id, double money) {

        if(customerRepository.findByIdAndUserRole(id, "CUSTOMER") == null) {
            throw new RuntimeException("Нет пользователя с " + id + " id");
        }

        Customer customer = customerRepository.findByIdAndUserRole(id, "CUSTOMER");

        if (money <= 0){
            throw new RuntimeException("Не возможно вывести такую сумму");
        }

        if (money <= customer.getWallet()) { // Делаем проверку, чтобы сумма указанная заказчиком была меньше кошелька
            customer.setWallet(customer.getWallet() - money);
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("Введенная Вами сумма превышает остаток на счете!");
        }
    }


    // Оставить Отзыв
    public void setReview(String text, int rate, Long id) {

        if(cookRepository.findByIdAndUserRole(id, "COOK") == null){
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

    //Заполнить карзину
    public void setBasket(List<Long> dishListId){
        Basket basket = new Basket();

        List<Dish> dishList = new ArrayList<>();

        for(Long i : dishListId){
            dishList.add(dishRepository.getById(i));
        }
        basket.setDishList(dishList);
        basketRepository.save(basket);
    }

    // Сделать Заказ
    public void makeOrder(String address, String phoneNumber, Long customerId,
                          Long cookId, List<Long> dishListId, Date date) { // Сделать заказ ()

        try {
            Order order = new Order();
            order.setPhoneNumber(phoneNumber);
            order.setAddress(address);
            order.setDateOrder(date);
            order.setOrderStatus(true);
            order.setCustomer(customerRepository.findByIdAndUserRole(customerId, "CUSTOMER"));
            order.setCook(cookRepository.findByIdAndUserRole(cookId, "COOK"));
            /**Сделать через карзину**/
//            for (Long a: dishListId) {
//                dishList.add(dishRepository.getById(a));
//            }
//            order.setDishes(dishList);
            orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }


    // Изменить карточку заказчика
    public Customer editCustomer(Long id, String login, String name,
                                 String lastName, double wallet) {

        Customer customer = customerRepository.findByIdAndUserRole(id, "CUSTOMER");

        try {
            customer.getUser().setLogin(login);
            customer.getUser().setName(name);
            customer.getUser().setLastName(lastName);
            customer.setWallet(wallet);
            return customerRepository.save(customer);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }


    // Отменить заказ
    public void cancelOrder(Long id) {
        Order order = orderRepository.getById(id);
        order.setOrderStatus(false); // Добавим сообщение !!!!!!!!!!!!!!!
        orderRepository.save(order);
    }


    // Регистрация
    public Customer registration(User user, String login, String password, String name, String lastName) {
        User newUser = user;
        newUser.setRole("CUSTOMER");
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setLastName(lastName);
        Customer newCustomer = new Customer();
        newCustomer.setUser(newUser);
        newCustomer.setWallet(0);
        userRepository.save(newUser);

        return customerRepository.save(newCustomer);
    }
}