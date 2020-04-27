package com.tinychiefdelights.controller;

import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;



@Api(value = "Работа с Поваром", tags = {"Повар"})
@RestController
public class CookController {

    //Injects через конструктор
    public CookController(CookRepository cookRepository, UserService userService) {
        this.cookRepository = cookRepository;
        this.userService = userService;
    }


    // Поля
    //
    private final CookRepository cookRepository;

    private final UserService userService;



    // GET MAPPING
    //


    // POST MAPPING
    //


    // PUT MAPPING
    //
    @PutMapping("/cook/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass){
        userService.changePassword(login, newPass);
    }

    // DELETE MAPPING
    //

}