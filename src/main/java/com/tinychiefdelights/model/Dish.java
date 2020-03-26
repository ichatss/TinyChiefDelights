package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Dish {

    public Dish(){ // Пустой конструктор для Hibernate

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
    private @Id @GeneratedValue Long id;

    private String dishName;

    private double dishCost;

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cook> cookList;

    private short weight;

    private short cookingTime;

    private String aboutDish;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private Order order;
}