package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Review {  // Отзыв

    public Review(){ // Пустой конструктор для Hibernate

    }

    public Review(String review, float rate){
        this.review = review;
        this.rate = rate;

    }

    // Поля
    private @Id @GeneratedValue Long id;

    private String review;

    private float rate;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private Cook cook;


    // Методы
    // ...
}