package com.tinychiefdelights.service;

// Типы повара
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

}