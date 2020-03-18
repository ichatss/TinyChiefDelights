package com.tinychiefdelights.model;

import java.util.List;

public class Cook {

    // Тип повара
    enum CookType{

        CHEF{
            // Методы
            void createDish(){

            }

            void editDish(){

            }

            void removeDish(){

            }
        }, CONFECTIONER, FISH_COOK, MEAT_COOK;

        // Общие поля на все ENUM
        double startSalary;

        }

    // Поля
    float rating;

    boolean cookStatus;

    List<Review> reviewList;

    String aboutCook;

}