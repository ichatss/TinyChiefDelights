package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.service.CustomerService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

import static com.tinychiefdelights.model.User.ROLE_CUSTOMER;


@Api(value = "Работа с Заказчиком", tags = {"Заказчик"})
@RestController
@RequestMapping("/customer")
@RolesAllowed({ROLE_CUSTOMER})
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

    //заполнить корзину
    @PostMapping("/basket")
    public void setBasket(@RequestParam List<Long> dishList){
        customerService.setBasket(dishList);
    }

    // Сделать заказ
    @PostMapping("/make/order")
    public void makeOrder(String address, String phoneNumber, Long customerId,
                          Long cookId, @RequestParam List<Long> dishList, Date date) {
        customerService.makeOrder(address, phoneNumber, customerId, cookId, dishList, date);
    }


    // Оставить отзыв
    @PostMapping("/set/review")
    public void setReview(String text, @RequestParam int rate, Long id){

        if(text == null || id == null){
            throw new RuntimeException("Заполните поля");
        }

        if(rate <= 0 || rate >= 5){
            throw new RuntimeException("Рейтинг должен быть от 1 до 5");
        }

        customerService.setReview(text, rate, id);
    }

    // Заказчик может редактировать свою карточку (поиск по ID)
    @PutMapping("/edit/{id}")
    Customer editCustomer(@PathVariable Long id, @RequestParam String login,
                          @RequestParam String name, @RequestParam String lastName, @RequestParam double wallet) {

        return customerService.editCustomer(id, login, name, lastName, wallet);
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
    public void cancelOrder(@PathVariable Long id) {
        customerService.cancelOrder(id);
    }


    // DELETE MAPPING
    //
}