package com.tinychiefdelights.model;

import java.util.List;

public class Dish {

    // Поля
    private String dishName;

    private double dishCost;

    private enum KitchenType{}

    private List<Cook> cookList;

    private short weight;

    private short cookingTime;

    private String aboutDish;

    // Методы
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishCost() {
        return dishCost;
    }

    public void setDishCost(double dishCost) {
        this.dishCost = dishCost;
    }

    public List<Cook> getCookList() {
        return cookList;
    }

    public void setCookList(List<Cook> cookList) {
        this.cookList = cookList;
    }

    public short getWeight() {
        return weight;
    }

    public void setWeight(short weight) {
        this.weight = weight;
    }

    public short getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(short cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getAboutDish() {
        return aboutDish;
    }

    public void setAboutDish(String aboutDish) {
        this.aboutDish = aboutDish;
    }
}
