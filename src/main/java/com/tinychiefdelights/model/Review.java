package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "review", schema = "public")
public class Review {  // Отзыв

    public Review() { // Пустой конструктор для Hibernate

    }


    // Поля
    //
    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "review")
    private String review;

    @Column(name = "rate")
    private float rate;


    //Relationships
    //Повар
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Cook cook;
}