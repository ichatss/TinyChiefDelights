package com.tinychiefdelights.controller;

import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.service.CookService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import static com.tinychiefdelights.model.User.ROLE_COOK;


@Api(value = "Работа с Поваром", tags = {"Повар"})
@RestController
@RequestMapping("/cook")
@RolesAllowed({ROLE_COOK})
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
    void createDish(@RequestParam Long id, @RequestParam String aboutDish, @RequestParam short cookingTime,
                    @RequestParam String dishName, @RequestParam short weight, @RequestParam double dishCost) {

        cookService.createDish(id, aboutDish, cookingTime, dishName, weight, dishCost);
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
    void editDish(Long id, String aboutDish, short cookingTime
            /*List<Cook> cookList*/, String dishName, short weight, double dishCost) {

        cookService.editDish(id, aboutDish, cookingTime, /*cookList*/ dishName, weight, dishCost);
    }


    // DELETE MAPPING
    //

}