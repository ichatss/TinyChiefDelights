package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tinychiefdelights.service.CookService;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cook", schema = "public")
public class Cook {

    public Cook(){ // Пустой конструктор для Hibernate

    }

    // Поля
    private @Id @GeneratedValue Long id;

    // ПООБЩАТЬСЯ С ЗУРАБОМ
    private CookService.CookType cookType;

    @Column(name = "rating")
    private float rating;

    @Column(name = "cook_status")
    private boolean cookStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cook_review",
            joinColumns = @JoinColumn(name = "review_id"))
    @JsonManagedReference // Таким образом я предотвратил рекурсию
    private List<Review> reviewList;

    @Column(name = "about_cook")
    private String aboutCook;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "cook_dish",
//            joinColumns = @JoinColumn(name = "dish_id"),
//            inverseJoinColumns = @JoinColumn(name = "cook_id"))
//    @JsonManagedReference // Таким образом я предотвратил рекурсию
//    private  List<Dish> dish;

    // Поля name, lastName, login, password наследуются от класса User;
}