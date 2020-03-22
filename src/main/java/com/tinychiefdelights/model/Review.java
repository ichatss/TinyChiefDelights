package com.tinychiefdelights.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Review {

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


    // Методы
    // ...
}