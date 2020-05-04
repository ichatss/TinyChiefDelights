package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    // SETTERS
    //
    // Injects into Setters
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



    // Методы
    //
    // Внести деньги на счет
    public void depositMoney(Long id, double money) {
        Customer customer = customerRepository.getByIdAndUserRole(id, Role.CUSTOMER);
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
        Customer customer = customerRepository.getByIdAndUserRole(id, Role.CUSTOMER);
        if (money <= customer.getWallet()) { // Делаем проверку, чтобы сумма указанная заказчиком была меньше кошелька
            customer.setWallet(customer.getWallet() - money);
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("Введенная Вами сумма превышает остаток на счете!");
        }
    }


    // Оставить Отзыв
    public void setReview(String text, int rate, Long id) {
        try {
            Review review = new Review();
            review.setReview(text);
            review.setRate(rate);
            review.setCook(cookRepository.getByIdAndUserRole(id, Role.COOK));
            reviewRepository.save(review);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

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
            order.setCustomer(customerRepository.getByIdAndUserRole(customerId, Role.CUSTOMER));
            order.setCook(cookRepository.getByIdAndUserRole(cookId, Role.COOK));

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
    public Customer editCustomer(Long id, User user, double wallet) {
        Customer customer = customerRepository.getByIdAndUserRole(id, Role.CUSTOMER);
        try {

            customer.setUser(user);
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
}