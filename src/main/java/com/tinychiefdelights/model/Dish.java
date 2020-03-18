package com.tinychiefdelights.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dish {

    // Поля
    private String dishName;

    private double dishCost;

    private enum KitchenType{}

    private List<Cook> cookList;

    private short weight;

    private short cookingTime;

    private String aboutDish;


    // Метод
    // ...
}
