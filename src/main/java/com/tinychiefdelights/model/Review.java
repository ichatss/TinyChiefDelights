package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "review", schema = "public")
public class Review {  // Отзыв

    public Review(){ // Пустой конструктор для Hibernate

    }

    public Review(String review, byte rate){ // Базовый конструктор
        this.review = review;
        this.rate = rate;
    }

    // Поля
    private @Id @GeneratedValue Long id;

    @Column(name = "review")
    private String review;

    @Column(name = "rate")
    private byte rate;

    @ManyToMany(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference // Таким образом я предотвратил рекурсию
    private List<Cook> cook;
}