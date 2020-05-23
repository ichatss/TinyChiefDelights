package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "review", schema = "public")
public class Review {  // Отзыв

    public Review() { // Пустой конструктор для Hibernate

    }


    // Поля
    //
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "review")
    private String review;

    @Column(name = "rate")
    private byte rate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

//    // Relationships
//    // Повар
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Cook cook;
}