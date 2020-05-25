package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pg_order", schema = "public")
public class Order {

    public Order() { // Пустой конструктор для Hibernate

    }


    // Поля
    //
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "order_status")
    private boolean orderStatus;

    @Column(name = "cost")
    private double cost;

    // Relationships
    //
    // Заказчик
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Проблема в том, что объекты загружаются лениво, и сериализация происходит до того,
    // как они будут загружены полностью.
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Решение проблемы
    private Customer customer;

    // Корзина
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;

    // Лист поваров
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_cook",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "cook_id"))
    @JsonIgnore
    private List<Cook> cookList;

    // Отзыв
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    @JsonIgnore
    private Review review;
}