package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.model.DishType;
import com.tinychiefdelights.service.CookService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import java.util.List;

import static com.tinychiefdelights.model.User.ROLE_CHEF;
import static com.tinychiefdelights.model.User.ROLE_COOK;


@Api(value = "Работа с Поваром", tags = {"Повар"})
@RestController
@RequestMapping("/cook")
@RolesAllowed({ROLE_COOK, ROLE_CHEF})
public class CookController {

    //Injects через конструктор
    @Autowired
    public CookController(UserService userService, CookService cookService) {
        this.userService = userService;
        this.cookService = cookService;
    }


    // Поля
    //
    private final UserService userService;

    private final CookService cookService;

    // GET MAPPING
    //


    // POST MAPPING
    //
    // Шеф-Повар создает блюдо для меню
    @RolesAllowed({ROLE_CHEF})
    @PostMapping("/create/dish")
    void createDish(@RequestParam String dishName, @RequestParam String aboutDish,
                    @RequestParam DishType dishType, @RequestParam short cookingTime,
                    @RequestParam short weight, @RequestParam double dishCost,
                    @RequestParam List<Long> cooksId) {

        // Валидация
        if (dishName.length() > 20) {
            throw new MainIllegalArgument("Название блюда должно содержать не более 20 символов!");
        }
        if (aboutDish.length() < 10) {
            throw new MainIllegalArgument("Описание блюда должно содержать не менее 10 символов!");
        }
        if (cookingTime > 3000 || cookingTime < 5) {
            throw new MainIllegalArgument("Блюдо не может готовится больше 6 часов и меньше 5 минут!");
        }
        if (weight > 30000 || weight < 20) {
            throw new MainIllegalArgument("Блюдо не может весить больше 30 кг. и меньше 20 грамм!");
        }
        if (dishCost < 100 || dishCost > 15000) {
            throw new MainIllegalArgument("Блюдо не может быть дороже 15000 руб. и дешевле 100 руб.!");
        }
        //

        cookService.createDish(dishName, aboutDish, dishType, cookingTime, weight, dishCost, cooksId);
    }


    // PUT MAPPING
    //
    // Изменить свой пароль
    @PutMapping("/change/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass) {

        // Валидация
        if (newPass.length() <= 5 || newPass.length() >= 16) {
            throw new MainIllegalArgument("Пароль должен быть не менее 6 символов и не более 18!");
        }
        //

        userService.changePassword(login, newPass);
    }


    // Изменить блюдо в меню
    @RolesAllowed({ROLE_CHEF})
    @PutMapping("/edit/dish/{id}")
    void editDish(@PathVariable Long id, @RequestParam String dishName, @RequestParam String aboutDish,
                  @RequestParam short cookingTime, @RequestParam short weight, @RequestParam DishType dishType,
                  @RequestParam double dishCost, @RequestParam List<Long> cooksId) {

        // Валидация
        if (dishName.length() > 20) {
            throw new MainIllegalArgument("Название блюда должно содержать не более 20 символов!");
        }
        if (aboutDish.length() < 10) {
            throw new MainIllegalArgument("Описание блюда должно содержать не менее 10 символов!");
        }
        if (cookingTime > 3000 || cookingTime < 5) {
            throw new MainIllegalArgument("Блюдо не может готовится больше 6 часов и меньше 5 минут!");
        }
        if (weight > 30000 || weight < 20) {
            throw new MainIllegalArgument("Блюдо не может весить больше 30 кг. и меньше 20 грамм!");
        }
        if (dishCost < 100 || dishCost > 15000) {
            throw new MainIllegalArgument("Блюдо не может быть дороже 15000 руб. и дешевле 100 руб.!");
        }
        //

        cookService.editDish(id, dishName, aboutDish, cookingTime, weight, dishCost, cooksId, dishType);
    }


    // DELETE MAPPING
    //
    // Удалить блюдо из меню
    @RolesAllowed({ROLE_CHEF})
    @DeleteMapping("/delete/dish/{id}")
    void removeDish(@PathVariable Long id) {
        cookService.removeDish(id);
    }
}