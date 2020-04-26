package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Dish;
import com.tinychiefdelights.repository.DishRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Работа с Блюдом", tags = {"Блюдо"})
@RestController
public class DishController {

    // Constructor
    //
    // Injects are here
    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    // Fields
    //
    private final DishRepository dishRepository;


    // GET MAPPING
    //
    // Вывод всех Блюд
    @GetMapping("/dishes")
    List<Dish> all() {
        return dishRepository.findAll();
    }


    // Вывод конкретного блюда по ID
    @GetMapping("/dishes/{id}")
    Dish one(@PathVariable Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    // POST MAPPING
    //
    // Создание нового Блюда
    @PostMapping("/dishes")
    Dish newDish(@RequestBody Dish newDish) {
        return dishRepository.save(newDish);
    }


    // PUT MAPPING
    //
    // Изменить Блюдо по ID
    @PutMapping("/dishes/{id}")
    Dish replaceDish(@RequestBody Dish newDish, @PathVariable Long id) {
        return dishRepository.findById(id)
                .map(dish -> {
                    dish.setDishName(newDish.getDishName());
                    dish.setDishCost(newDish.getDishCost());
                    dish.setCookingTime(newDish.getCookingTime());
                    dish.setWeight(newDish.getWeight());
                    dish.setAboutDish(newDish.getAboutDish());
                    dish.setCookList(newDish.getCookList());
                    return dishRepository.save(dish);
                })
                .orElseGet(() -> {
                    newDish.setId(id);
                    return dishRepository.save(newDish);
                });
    }


    // DELETE MAPPING
    //
    // Удалить Блюдо по ID !!!!!!!!!! ДОДЕЛАТЬ
    @DeleteMapping("/dishes/{id}")
    void deleteDish(@PathVariable Long id) {
        dishRepository.deleteById(id);
    }
}