package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Aggregate Root
    @GetMapping("/orders")
    List<Order> all(){
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    Order newOrder (@RequestBody Order newOrder){
        return orderRepository.save(newOrder);
    }

    //Single Item
    @GetMapping("/orders/{id}")
    Order one(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/orders/{id}")
    Order replaceOrder(@RequestBody Order newOrder, @PathVariable Long id){
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomer(newOrder.getCustomer());
                    order.setAddress(newOrder.getAddress());
                    order.setPhoneNumber(newOrder.getPhoneNumber());
                    order.setDateOrder(newOrder.getDateOrder());
                    order.setCook(newOrder.getCook());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setId(id);
                    return orderRepository.save(newOrder);
                });
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
    }
}