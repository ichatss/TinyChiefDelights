package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Dish {

    public Dish(){}

    public Dish(String dishName, double dishCost,
                List<Cook> cookList, short weight,
                short cookingTime, String aboutDish) {

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

    private enum KitchenType{}

    @OneToMany(mappedBy = "dish", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cook> cookList;

    private short weight;

    private short cookingTime;

    private String aboutDish;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private Order order;


    // Метод
    // ...
}
