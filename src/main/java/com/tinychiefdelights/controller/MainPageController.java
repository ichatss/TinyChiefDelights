package com.tinychiefdelights.controller;

import com.tinychiefdelights.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Api(value = "Главная страница", tags = {"Главное"})
@RestController
@RequestMapping("/tinychief")
@RolesAllowed({})
public class MainPageController {

    // Fields
    //
    private CustomerService customerService;


    // Injects are here in SETTERS
    //
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/registration")
    void registration(String name, String lastName, String login, String password) {
        customerService.registration(name, lastName, login, password);
    }
}