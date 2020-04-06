package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tinychiefdelights.service.CookService;
import com.tinychiefdelights.service.CookType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cook", schema = "public")
public class Cook extends User {

    public Cook(){ // Пустой конструктор для Hibernate

    }

    public Cook(String name, String lastName, String role, String login, String password,
                CookType cookType, float rating, boolean cookStatus,
                List<Review> reviewList, String aboutCook){ // Вызываем родительский конструктор вместе со своими полями

        super(name, lastName, role, login, password);
        this.cookType = cookType;
        this.rating = rating;
        this.cookStatus = cookStatus;
        this.reviewList = reviewList;
        this.aboutCook = aboutCook;
    }

    // Поля
    private @Id @GeneratedValue Long id;

    @OneToOne(mappedBy = "")
    private User user;

    private CookType cookType;

    @Column(name = "rating")
    private float rating;

    @Column(name = "cook_status")
    private boolean cookStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cook_review",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "cook_id"))
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