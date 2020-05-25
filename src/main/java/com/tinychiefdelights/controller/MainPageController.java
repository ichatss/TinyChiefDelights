package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
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
    void registration(@RequestParam String name, @RequestParam String lastName,
                      @RequestParam String login, @RequestParam String password, @RequestParam String password2) {

        // Валидация
        if (name.length() <= 1 || name.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (lastName.length() <= 1 || lastName.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (password.length() <= 5 || password.length() >= 16) {
            throw new MainIllegalArgument("Пароль должен быть не менее 6 символов и не более 18!");
        }
        if (login.length() <= 3 || login.length() >= 23) {
            throw new MainIllegalArgument("Логин должен быть не менее 4 символов и не более 24!");
        }
        //

        userService.registration(name, lastName, login, password, password2);

    }
}