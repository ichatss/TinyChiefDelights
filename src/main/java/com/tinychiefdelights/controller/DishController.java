package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Dish;
import com.tinychiefdelights.repository.DishRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DishController {

    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    // Aggregate Root
    @GetMapping("/dishes")
    List<Dish> all(){
        return dishRepository.findAll();
    }

    @PostMapping("/dishes")
    Dish newDish (@RequestBody Dish newDish){
        return dishRepository.save(newDish);
    }

    //Single Item
    @GetMapping("/dishes/{id}")
    Dish one(@PathVariable Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/dishes/{id}")
    Dish replaceDish(@RequestBody Dish newDish, @PathVariable Long id){
        return dishRepository.findById(id)
                .map(dish -> {
                    dish.setDishName(newDish.getDishName());
                    dish.setDishCost(newDish.getDishCost());
                    dish.setCookingTime(newDish.getCookingTime());
                    dish.setWeight(newDish.getWeight());
                    dish.setAboutDish(newDish.getAboutDish());
                    return dishRepository.save(dish);
                })
                .orElseGet(() -> {
                    newDish.setId(id);
                    return dishRepository.save(newDish);
                });
    }

    @DeleteMapping("/dishes/{id}")
    void deleteDish(@PathVariable Long id){
        dishRepository.deleteById(id);
    }
}