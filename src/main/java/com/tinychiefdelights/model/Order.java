package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Order {

    public Order(){ // Пустой конструктор для Hibernate

    }

    public Order(short orderNumber, Customer customer, String address,
                 String phoneNumber, Date dateOrder, Cook cook,
                 List<Dish> dishes, boolean orderStatus) { // Базовый конструктор

        this.orderNumber = orderNumber;
        this.customer = customer;
        this.address = address;
        this.phoneNumber= phoneNumber;
        this.dateOrder = dateOrder;
        this.cook = cook;
        this.dishes = dishes;
        this.orderStatus = orderStatus;
    }

    // Поля
    private @Id @GeneratedValue Long id;

    private short orderNumber;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    private String address;

    private String phoneNumber;

    private Date dateOrder;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private Cook cook;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Dish> dishes;

    private boolean orderStatus;
}