package com.tinychiefdelights.service;

import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    // Поля
    //
    // Inject через SETTERS
    private OrderRepository orderRepository;


    // Setters
    //
    // Injects находятся здесь
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    // Методы
    public double calculateCost() { // Посчитать стоимость заказа
        return 0; // умножат рейтш повара на цену блюда + если шеф доплата 500
    }


    public void setReview() { // Оставить отзыв и оценку ()

    }


}