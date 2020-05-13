package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
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
    public void setDishRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    // Методы
    //
    // Добавить блюдо
    ////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Проверку добавить
    public void createDish(Long id, String aboutDish, short cookingTime,
                           String dishName, short weight, double dishCost) {

        Dish dish = new Dish();
        dish.setDishName(dishName);
        dish.setDishCost(dishCost);
        dish.setWeight(weight);
        dish.setCookingTime(cookingTime);
//        dish.setCookList(cookRepository.getCookByIdList(id)); // ИСПРАВИТЬ!!!!
        dish.setAboutDish(aboutDish);

        dishRepository.save(dish);

    }


    // Изменить карту блюда
    public void editDish(Long id, String aboutDish, short cookingTime,
                         /*List<Cook> cookList*/ String dishName, short weight, double dishCost) {

        Long newId = id;

        if (newId != null) {
            Dish dish = dishRepository.getById(id);

            dish.setDishName(dishName);
            dish.setDishCost(dishCost);
            dish.setWeight(weight);
            dish.setCookingTime(cookingTime);
//            dish.setCookList(cookList);
            dish.setAboutDish(aboutDish);

            dishRepository.save(dish);
        } else {
            throw new NotFoundException(newId);
        }
    }


    // Удалить блюдо
    void removeDish(Long id) {

        Long newId = id;

        if(newId != null) {
            Dish dish = dishRepository.getById(id);
            dishRepository.delete(dish);
        } else {
            throw new NotFoundException(newId);
        }
    }
}