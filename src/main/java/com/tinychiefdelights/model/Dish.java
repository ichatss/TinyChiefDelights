package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "dish", schema = "public")
public class Dish {

    public Dish() { // Пустой конструктор для Hibernate

    }

    public Dish(String dishName, double dishCost, List<Cook> cookList,
                short weight, short cookingTime, String aboutDish) { // Базовый конструктор

        this.dishName = dishName;
        this.dishCost = dishCost;
        this.cookList = cookList;
        this.weight = weight;
        this.cookingTime = cookingTime;
        this.aboutDish = aboutDish;
    }


    // Поля
    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "name")
    private String dishName;

    @Column(name = "dish_cost")
    private double dishCost;

    @Column(name = "weight")
    private short weight;

    @Column(name = "cooking_time")
    private short cookingTime;

    @Column(name = "about_dish")
    private String aboutDish;


    //Relationships

    // Лист поваров
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cook_dish",
            joinColumns = @JoinColumn(name = "cook_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @JsonBackReference // Таким образом я предотвратил рекурсию
    private List<Cook> cookList;

    // Лист Заказов в которых находятся блюда
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference // Таким образом я предотвратил рекурсию
    private List<Order> ordersList;
}