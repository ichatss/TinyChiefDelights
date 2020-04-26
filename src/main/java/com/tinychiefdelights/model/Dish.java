package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "dish", schema = "public")
public class Dish {

    public Dish() { // Пустой конструктор для Hibernate

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
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Cook> cookList;

    // Лист Заказов в которых находятся блюда
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Order> ordersList;
}