package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "cook", schema = "public")
public class Cook {

    public Cook() { // Пустой конструктор для Hibernate

    }

    // Поля

    // name, lastName, login, password берем от класса User через связи;

    private @Id
    @GeneratedValue
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CookType cookType;

    @Column(name = "rating")
    private double rating;

    @Column(name = "cook_status")
    private boolean cookStatus;

    @Column(name = "about_cook")
    private String aboutCook;

    @Column(name = "start_salary")
    private float startSalary;


    //Relationships
    //
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Join without Cook in User class
    private User user;

    // Лист блюд
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "cook_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "cook_id"))
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Dish> dish;

    // Лист заказов
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_cook",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "cook_id"))
    @JsonIgnore
    private List<Order> orderList;
}