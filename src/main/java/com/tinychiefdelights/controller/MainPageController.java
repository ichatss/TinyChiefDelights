package com.tinychiefdelights.controller;

import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "Главная страница", tags = {"Главное"})
@RestController
@RequestMapping("/tinychief")
public class MainPageController {

    // Fields
    //
    private UserService userService;


    // Injects are here in SETTERS
    //
    @Autowired
    public void setCustomerService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    void registration(String name, String lastName, String login, String password) {
        userService.registration(name, lastName, login, password);
    }
}