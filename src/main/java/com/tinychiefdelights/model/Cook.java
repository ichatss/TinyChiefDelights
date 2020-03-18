package com.tinychiefdelights.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cook extends User {

    // Тип повара
     private enum CookType{

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
    private float rating;

    private boolean cookStatus;

    private List<Review> reviewList;

    private String aboutCook;


    // Методы
    //...
}