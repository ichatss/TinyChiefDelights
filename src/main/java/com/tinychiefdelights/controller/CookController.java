package com.tinychiefdelights.controller;

import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.service.CookService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

import java.util.List;

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
    void createDish(@RequestParam Long id, @RequestParam String dishName, @RequestParam String aboutDish,
                    @RequestParam short cookingTime, @RequestParam short weight,
                    @RequestParam double dishCost, @RequestParam List<Long> cooksId) {

        cookService.createDish(id, dishName, aboutDish, cookingTime, weight, dishCost, cooksId);
    }


    // PUT MAPPING
    //
    // Изменить свой пароль
    @PutMapping("/change/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass) {
        userService.changePassword(login, newPass);
    }


    // Изменить блюдо в меню
//    @PreAuthorize("#cookRepository.getByIdAndUserRole(#id, 'COOK').get().cookType == 'CHEF'")
    @PutMapping("/edit/dish/{id}")
    void editDish(@PathVariable Long id, @RequestParam String dishName, @RequestParam String aboutDish,
                  @RequestParam short cookingTime, @RequestParam short weight,
                  @RequestParam double dishCost, @RequestParam List<Long> cooksId) {

        cookService.editDish(id, dishName, aboutDish, cookingTime, weight, dishCost, cooksId);
    }


    // DELETE MAPPING
    //
    // Удалить блюдо из меню
    @DeleteMapping("/delete/dish/{id}")
    void removeDish(@PathVariable Long id) {
        cookService.removeDish(id);
    }
}