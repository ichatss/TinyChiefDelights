package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> { // Интерфейс для репозитория Dish
    Dish getById(Long id);
}
