package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.service.CustomerService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Api(value = "Работа с Заказчиком", tags = {"Заказчик"})
@RestController
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


    // PUT MAPPING
    //
    // Заказчик может редактировать свою карточку (поиск по ID)
    @PutMapping("/customer/{id}")
    Customer editCustomer(@PathVariable Long id, User user, @RequestParam double wallet) {
        return customerService.editCustomer(id, user, wallet);
    }


    // Снять деньги со своего депозита (Заказчик)
    @PutMapping("/customer/{id}/withdraw/{money}")
    void withdrawMoney(@PathVariable Long id, @RequestParam double money) {

        customerService.withdrawMoney(id, money);
    }

    @PutMapping("/customer/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass){
        userService.changePassword(login, newPass);
    }

    @PutMapping("/customer/{id}/money")
    public void depositMoney(@PathVariable Long id, @RequestParam double money) { // Внести деньги на счет ()
        customerService.depositMoney(id, money);
    }
    // DELETE MAPPING
    //
}