package com.tinychiefdelights.service;

import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Dish;
import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookService extends UserService {

    // Поля
    //
    // Injects with SETTERS
    private CookRepository cookRepository;

    private DishRepository dishRepository;

    // Setters
    //
    @Autowired
    public void setCookRepository(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    @Autowired
    public void setDishRepository(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }



    // Методы
    //
    // Добавить блюдо
     ////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Проверку добавить
    public void createDish(String aboutDish, short cookingTime,
                    List<Cook> cookList, String dishName, short weight, double dishCost) {

        Dish dish = new Dish();
        dish.setDishName(dishName);
        dish.setDishCost(dishCost);
        dish.setWeight(weight);
        dish.setCookingTime(cookingTime);
        dish.setCookList(cookList);
        dish.setAboutDish(aboutDish);

        dishRepository.save(dish);

    }


    // Изменить карту блюда // Добавить ПРОВЕРКУ!!!!!!!!!!!!!!!!!!!!!!!!!
    public void editDish(Long id, String aboutDish, short cookingTime,
                  List<Cook> cookList, String dishName, short weight, double dishCost) {

        Dish dish = dishRepository.getById(id);

        dish.setDishName(dishName);
        dish.setDishCost(dishCost);
        dish.setWeight(weight);
        dish.setCookingTime(cookingTime);
        dish.setCookList(cookList);
        dish.setAboutDish(aboutDish);

        dishRepository.save(dish);
    }


    // Удалить блюдо // ДОБАВИТЬ ПРОВЕРКУ!!!!!!!!!!!!!!!!!!!!!!!!
    void removeDish(Long id) {

        Dish dish = dishRepository.getById(id);

        dishRepository.delete(dish);
    }
}