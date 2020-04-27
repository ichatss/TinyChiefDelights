package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.service.CustomerService;
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
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }


    // Fields
    // Injects into constructor
    //
    private final CustomerRepository customerRepository;

    private final CustomerService customerService;


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


    // DELETE MAPPING
    //

}