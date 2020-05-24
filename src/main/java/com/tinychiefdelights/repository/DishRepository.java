package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DishRepository extends JpaRepository<Dish, Long> { // Интерфейс для репозитория Dish

    // Берем Блюдо по ID
    Dish getById(Long id);

    Dish findByDishName(String dishName);
}