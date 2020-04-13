package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.List;

@Data
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

    @Column(name = "rating")
    private float rating;

    @Column(name = "cook_status")
    private boolean cookStatus;

    @Column(name = "about_cook")
    private String aboutCook;

    // private CookType cookType; РАСКОМЕНТИРУЕМ В ХОДЕ РАБОТЫ С ТИПОМ ПОВАРОВ


    // Relationships
    //
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Join without Cook in User class
    private User user;

    // Лист отзывов
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cook",
            joinColumns = @JoinColumn(name = "review_id"))
    @JsonManagedReference // Таким образом я предотвратил рекурсию
    private List<Review> reviewList;

    // Лист блюд
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cook_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "cook_id"))
    @JsonManagedReference // Таким образом я предотвратил рекурсию
    private List<Dish> dish;
}