package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.repository.OrderRepository;
import com.tinychiefdelights.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Работа с Заказом", tags = {"Заказ"})
@RestController
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
    // Вывод всех Заказов
    @GetMapping("/orders")
    List<Order> all() {
        return orderRepository.findAll();
    }

    // Вывод заказа по ID
    @GetMapping("/orders/{id}")
    Order one(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    // POST MAPPING
    //
    // Создать новый Заказ !!!!!!!!!!!!!!!!!!!! ДОДЛЕАТЬ
    @PostMapping("/orders")
    Order newOrder(@RequestBody Order newOrder) {
        return orderRepository.save(newOrder);
    }


    // PUT MAPPING
    //
    // Изменить Заказ по ID
    @PutMapping("/orders/{id}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomer(newOrder.getCustomer());
                    order.setAddress(newOrder.getAddress());
                    order.setPhoneNumber(newOrder.getPhoneNumber());
                    order.setDateOrder(newOrder.getDateOrder());
                    order.setCook(newOrder.getCook());
                    order.setDishes(newOrder.getDishes());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setId(id);
                    return orderRepository.save(newOrder);
                });
    }


    // Отменить Заказ
    @PutMapping("/order/{id}/cancelOrder")
    void cancelOrder(@PathVariable Long id){
        orderService.cancelOrder(id);
    }


    // DELETE MAPPING
    //
    // Удалить Заказ по ID
    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}