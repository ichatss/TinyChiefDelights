package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
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

    //private CookType cookType;

    @Column(name = "rating")
    private float rating;

    @Column(name = "cook_status")
    private boolean cookStatus;

    @Column(name = "about_cook")
    private String aboutCook;


    //Relationships
    //
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Join without Cook in User class
    private User user;

    // Лист отзывов
    @OneToMany(mappedBy = "cook", cascade = CascadeType.ALL)
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Review> reviewList;

    // Лист поваров
    @OneToMany(mappedBy = "cook", cascade = CascadeType.ALL)
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Order> orderList;

    // Лист блюд
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cook_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "cook_id"))
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Dish> dish;

}