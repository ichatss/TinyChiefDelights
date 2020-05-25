package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNotFound;
import com.tinychiefdelights.exceptions.MainNullPointer;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Dish;
import com.tinychiefdelights.model.DishType;
import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    // Удалить блюдо
    public void removeDish(Long id) {

        try {
            dishRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new MainNotFound(id);
        }
    }


    // Добавить блюдо
    public Dish createDish(String dishName, String aboutDish, DishType dishType,
                           short cookingTime, short weight, double dishCost, List<Long> cooksId) {

        Dish dish = new Dish(); //Создаем новое блюдо

        // Ставим значения его полям
        if (!dishRepository.existsByDishName(dishName)) { // Делаем проверку, чтобы блюда с таким названием не было
            dish.setDishName(dishName);
        } else {
            throw new MainIllegalArgument("Блюдо с таким названием уже существует!");
        }
        dish.setDishCost(dishCost);
        dish.setWeight(weight);
        dish.setCookingTime(cookingTime);
        dish.setDishType(dishType);
        // Создаем коллекцию, чтобы передать туда все принимаемые значения
        List<Cook> cookList = new ArrayList<>();

        try {
            for (Long i : cooksId) { // Добавляем в коллекцию принимаемых поваров
                cookList.add(cookRepository.getCookById(i));
            }
        } catch (NullPointerException ex) {
            throw new MainNullPointer("Повара с таким id нет!");
        }
        dish.setCookList(cookList); // С коллекциями проделывать такое BadPractise (для hibernate)
        dish.setAboutDish(aboutDish);

        return dishRepository.save(dish); // save
    }


    // Изменить карту блюда
    public void editDish(Long id, String dishName, String aboutDish, short cookingTime,
                         short weight, double dishCost, List<Long> cooksId, DishType dishType) {

        try {

            // Мы забираем из БД нужное нам блюдо
            Dish dish = dishRepository.getById(id);

            dish.setDishCost(dishCost);
            dish.setCookingTime(cookingTime);
            dish.setWeight(weight);
            dish.setDishType(dishType);

            List<Cook> cookList = new ArrayList<>(); // Создаем коллекцию, чтобы передать туда все принимаемые значения

            try {
                for (Long i : cooksId) { // Добавляем в коллекцию принимаемых поваров
                    cookList.add(cookRepository.getCookById(i));
                }
            } catch (NullPointerException ex) {
                throw new MainNotFound(id);
            }

            dish.setCookList(cookList); // С коллекциями проделывать такое BadPractise (для hibernate)
            dish.setAboutDish(aboutDish);
            dishRepository.save(dish); // Save edits

        } catch (NullPointerException ex) {
            throw new MainNotFound(id);
        }
    }
}