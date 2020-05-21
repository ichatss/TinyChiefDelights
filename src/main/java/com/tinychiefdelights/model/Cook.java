package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "^")
    @Column(name = "rating")
    private float rating;

    @Column(name = "cook_status")
    private boolean cookStatus;

    @Column(name = "about_cook")
    private String aboutCook;


    //Relationships
    //
    @OneToOne(cascade = CascadeType.ALL)
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "cook_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "cook_id"))
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Dish> dish;
}