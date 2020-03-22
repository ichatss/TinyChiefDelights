package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Order {

    private @Id @GeneratedValue Long id;


    // Поля
    private short orderNumber;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
    private Customer customer;

    private String address;

    private String phoneNumber;

    private Date dateOrder;

    private Cook cook;

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
