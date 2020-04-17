package com.tinychiefdelights.service;

import com.tinychiefdelights.repository.CookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Типы повара
@Service
public enum CookType {

    CHEF {
        // Методы
        void createDish() {

        }

        void editDish() {

        }

        void removeDish() {

        }
    }, CONFECTIONER, FISH_COOK, MEAT_COOK;

    // Общие поля на все ENUM
    double startSalary; // Начальная ставка

    CookRepository cookRepository; // Повар; DI через сеттер

    @Autowired
    public void setCookRepository(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }
}