package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
