package com.tinychiefdelights.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {

    // Поля
    private short orderNumber;

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
