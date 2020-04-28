package com.tinychiefdelights.controller;

import com.tinychiefdelights.repository.OrderRepository;
import com.tinychiefdelights.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "Работа с Заказом", tags = {"Заказ"})
@RestController
@RequestMapping("/order")
public class OrderController {

    // Constructor
    //
    // Injects are here
    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }


    // Fields
    //
    private final OrderRepository orderRepository;

    private final OrderService orderService;


    // GET MAPPING
    //


    // POST MAPPING
    //


    // PUT MAPPING
    //

    // DELETE MAPPING
    //

}