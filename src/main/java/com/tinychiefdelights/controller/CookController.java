package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.service.CookService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Работа с Поваром", tags = {"Повар"})
@RestController
@RequestMapping("/cook")
public class CookController {

    //Injects через конструктор
    @Autowired
    public CookController(CookRepository cookRepository, UserService userService, CookService cookService) {
        this.cookRepository = cookRepository;
        this.userService = userService;
        this.cookService = cookService;
    }


    // Поля
    //
    private final CookRepository cookRepository;

    private final UserService userService;

    private final CookService cookService;

    // GET MAPPING
    //


    // POST MAPPING
    //
    // Шеф-Повар создает блюдо для меню
    @PostMapping("/create/dish")
    void createDish(String aboutDish, short cookingTime,
                    List<Cook> cookList, String dishName, short weight, double dishCost){

        cookService.createDish(aboutDish, cookingTime, cookList, dishName, weight, dishCost);
    }


    // PUT MAPPING
    //
    // Изменить свой пароль
    @PutMapping("/change/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass) {
        userService.changePassword(login, newPass);
    }


    // Изменить блюдо в меню
    @PutMapping("/edit/dish")
    void editDish(Long id, String aboutDish, short cookingTime,
                  List<Cook> cookList, String dishName, short weight, double dishCost){

        cookService.editDish(id, aboutDish, cookingTime, cookList, dishName, weight, dishCost);
    }


    // DELETE MAPPING
    //

}