package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.service.CustomerService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Api(value = "Работа с Заказчиком", tags = {"Заказчик"})
@RestController
@RequestMapping("/customer")
@Secured("CUSTOMER")
public class CustomerController {

    //Constructor
    //
    // Injects через конструктор
    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService, UserService userService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.userService = userService;
    }


    // Fields
    // Injects into constructor
    //
    private final CustomerRepository customerRepository;

    private final CustomerService customerService;

    private final UserService userService;


    // GET MAPPING
    //


    // POST MAPPING
    //
    // Сделать заказ
    @PostMapping("/make/order")
    public void makeOrder(String address, String phoneNumber, Long customerId,
                          Long cookId, @RequestParam List<Long> dishList, Date date) {
        customerService.makeOrder(address, phoneNumber, customerId, cookId, dishList, date);
    }


    // Регистрация
    @PostMapping("/registration")
    public void registration(User user, String login, String password, String name, String lastName){
        customerService.registration(user, login, password, name, lastName);
    }


    // Оставить отзыв
    @PostMapping("/set/review")
    public void setReview(String text, int rate, Long id) {
        customerService.setReview(text, rate, id);
    }

    // PUT MAPPING
    //
    // Заказчик может редактировать свою карточку (поиск по ID)
    @PutMapping("/edit/{id}")
    Customer editCustomer(@PathVariable Long id, User user, @RequestParam double wallet) {
        return customerService.editCustomer(id, user, wallet);
    }


    // Снять деньги со своего депозита (Заказчик)
    @PutMapping("/{id}/withdraw/{money}")
    void withdrawMoney(@PathVariable Long id, @RequestParam double money) {
        customerService.withdrawMoney(id, money);
    }


    // Изменить свой пароль
    @PutMapping("/change/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass) {
        userService.changePassword(login, newPass);
    }


    // Внести деньги на счет (Заказчик)
    @PutMapping("/{id}/deposit/money")
    public void depositMoney(@PathVariable Long id, @RequestParam double money) {
        customerService.depositMoney(id, money);
    }


    // Отменить Заказ
    @PutMapping("/cancel/order/{id}")
    public void cancelOrder(@PathVariable Long id){
        customerService.cancelOrder(id);
    }


    // DELETE MAPPING
    //
}