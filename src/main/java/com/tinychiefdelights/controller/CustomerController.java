package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.service.CustomerService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
    public CustomerController(CustomerRepository customerRepository,
                              CustomerService customerService,
                              UserService userService) {

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


    // GET
    //
    // Посмотреть меню
    @GetMapping("/menu")
    List<Dish> getMenu(){
        return customerService.getMenu();
    }


    // POST
    //
    // Заполнить корзину
    @PostMapping("/basket")
    public void setBasket(@RequestParam List<Long> dishList) {
        customerService.setBasket(dishList);
    }


    // Сделать заказ
    @PostMapping("/make/order")
    public void makeOrder(String address, String phoneNumber, Long basketId, @RequestParam("dateInput")
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateInput) {

        customerService.makeOrder(address, phoneNumber, basketId, dateInput);
    }


    // Оставить отзыв
    @PostMapping("/set/review")
    public void setReview(@RequestParam String text, @RequestParam int rate, @RequestParam Long id) {

        customerService.setReview(text, rate, id);
    }


    // PUT
    //
    // Заказчик может редактировать свою карточку (поиск по ID)
    @PutMapping("/edit")
    Customer editCustomer(@RequestParam String login,
                          @RequestParam String name, @RequestParam String lastName) {

        return customerService.editCustomer(login, name, lastName);
    }


    // Снять деньги со своего депозита (Заказчик)
    @PutMapping("/withdraw/{money}")
    void withdrawMoney(@RequestParam double money) {
        customerService.withdrawMoney(money);
    }


    // Изменить свой пароль
    @PutMapping("/change/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass) {
        userService.changePassword(login, newPass);
    }


    // Внести деньги на счет (Заказчик)
    @PutMapping("/deposit/money")
    public void depositMoney(@RequestParam double money) {
        customerService.depositMoney(money);
    }


    // Отменить Заказ
    @PutMapping("/cancel/order/{id}")
    public void cancelOrder(@PathVariable Long id) {
        customerService.cancelOrder(id);
    }


    // DELETE MAPPING
    //
}