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

    public Cook(){

    }

    public Cook(String name, String lastName, float rating, boolean cookStatus,
                List<Review> reviewList, String aboutCook, CookType cookType){
         super(name, lastName);
         this.rating = rating;
         this.cookStatus = cookStatus;
         this.reviewList = reviewList;
         this.aboutCook = aboutCook;
         this.cookType = cookType;
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


    // Методы
    //...
}