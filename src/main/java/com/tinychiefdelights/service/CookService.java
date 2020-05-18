package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainNotFound;
import com.tinychiefdelights.exceptions.MainNullPointer;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Dish;
import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Dish createDish(Long id, String dishName, String aboutDish,
                           short cookingTime, short weight, double dishCost, List<Long> cooksId) {

        //Создаем новое блюдо
        Dish dish = new Dish();

        // Ставим значения его полям
        dish.setDishName(dishName);
        dish.setDishCost(dishCost);
        dish.setWeight(weight);
        dish.setCookingTime(cookingTime);
        // Создаем коллекцию, чтобы передать туда все принимаемые значения
        List<Cook> cookList = new ArrayList<>();

        for (Long i : cooksId) { // Добавляем в коллекцию принимаемых поваров
            cookList.add(cookRepository.getCookById(i));
        }

        dish.setCookList(cookList); // С коллекциями проделывать такое BadPractise (для hibernate)
        dish.setAboutDish(aboutDish);

        return dishRepository.save(dish); // save

    }


    // Изменить карту блюда
    public void editDish(Long id, String dishName, String aboutDish, short cookingTime,
                         short weight, double dishCost, List<Long> cooksId) {

        try {

            // Мы забираем из БД нужное нам блюдо
            Dish dish = dishRepository.getById(id);

                dish.setDishCost(dishCost);
                dish.setDishName(dishName);
                dish.setCookingTime(cookingTime);
                dish.setWeight(weight);
                // Создаем коллекцию, чтобы передать туда все принимаемые значения
                List<Cook> cookList = new ArrayList<>();

                for (Long i : cooksId) { // Добавляем в коллекцию принимаемых поваров
                    cookList.add(cookRepository.getCookById(i));
                }

                dish.setCookList(cookList); // С коллекциями проделывать такое BadPractise (для hibernate)
                dish.setAboutDish(aboutDish);

                dishRepository.save(dish); // Save edits

        }catch (NullPointerException ex) {
            throw new MainNullPointer("Блюдо с таким ID не найдено!");
        }
    }


    // Удалить блюдо
    public void removeDish(Long id) {

        if (id != null) {
            Dish dish = dishRepository.getById(id);
            dishRepository.delete(dish);
        } else {
            throw new MainNotFound(id);
        }
    }
}