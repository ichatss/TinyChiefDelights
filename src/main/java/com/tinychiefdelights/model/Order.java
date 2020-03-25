package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Order {

    public Order(){

    }

    public Order(short orderNumber, Customer customer, String address,
                 String phoneNumber, Date dateOrder, Cook cook, List<Dish> dishes) {

        this.orderNumber = orderNumber;
        this.customer = customer;
        this.address = address;
        this.phoneNumber= phoneNumber;
        this.dateOrder = dateOrder;
        this.cook = cook;
        this.dishes = dishes;
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

    private enum orderStatus{}


    // Методы
    public double calculateCost(){// Посчитать стоимость заказа
        return 0;
    }

    public void setReview(){// Оставить отзыв и оценку

    }

    public void cancelOrder(){// Отменить заказ

    }
}