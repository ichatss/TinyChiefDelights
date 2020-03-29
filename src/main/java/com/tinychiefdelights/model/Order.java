package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pg_order", schema = "public")
public class Order {

    public Order(){ // Пустой конструктор для Hibernate

    }

    public Order(/*Customer customer,*/ String address,
                 String phoneNumber, Date dateOrder, /* Cook cook,*/
                 List<Dish> dishes, boolean orderStatus) { // Базовый конструктор

//        this.customer = customer;
        this.address = address;
        this.phoneNumber= phoneNumber;
        this.dateOrder = dateOrder;
//        this.cook = cook;
        this.dishes = dishes;
        this.orderStatus = orderStatus;
    }

    // Поля
    private @Id @GeneratedValue Long id;


//    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
//    @JsonBackReference
//    private Customer customer;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_order")
    private Date dateOrder;


//    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
//    @JsonBackReference
//    private Cook cook;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "pg_order_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Dish> dishes;

    @Column(name = "order_status")
    private boolean orderStatus;
}