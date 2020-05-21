package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.User;
import com.tinychiefdelights.service.CustomerService;
import io.swagger.annotations.Api;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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