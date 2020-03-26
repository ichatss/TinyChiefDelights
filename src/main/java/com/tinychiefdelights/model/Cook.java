package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Cook extends User {

    // Типы повара
     private enum CookType{

        CHEF{
            // Методы
            void createDish(){

            }

            void editDish(){

            }

            void removeDish(){

            }
        }, CONFECTIONER, FISH_COOK, MEAT_COOK;

        // Общие поля на все ENUM
        double startSalary;

        }

    public Cook(){ // Пустой конструктор для Hibernate

    }

    public Cook(String name, String lastName, String login, String password,
                CookType cookType, float rating, boolean cookStatus,
                List<Review> reviewList, String aboutCook){ // Вызываем родительский конструктор вместе со своими полями

        super(name, lastName, login, password);
        this.cookType = cookType;
        this.rating = rating;
        this.cookStatus = cookStatus;
        this.reviewList = reviewList;
        this.aboutCook = aboutCook;
    }

    // Поля
    private @Id @GeneratedValue Long id;

    private CookType cookType;

    private float rating;

    private boolean cookStatus;

    @OneToMany(mappedBy = "cook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviewList;

    private String aboutCook;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private  Dish dish;

    // Поля name, lastName, login, password наследуются от класса User;
}