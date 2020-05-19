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
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "order_status")
    private boolean orderStatus;


    // Relationships
    //
    // Заказчик
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Проблема в том, что объекты загружаются лениво, и сериализация происходит до того, как они будут загружены полностью.
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    // Лист блюд
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "order_dish",
//            joinColumns = @JoinColumn(name = "dish_id"),
//            inverseJoinColumns = @JoinColumn(name = "order_id"))
//    private List<Dish> dishes;
    @Column(name = "basket_id")
    private Long basketId;

    // Повар
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Проблема в том, что объекты загружаются лениво, и сериализация происходит до того, как они будут загружены полностью.
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cook cook;
}