package com.tinychiefdelights.model;

import java.util.Date;
import java.util.List;

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

    // Getters and Setters
    public short getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(short orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    // Методы
    public double calculateCost(){// Посчитать стоимость заказа
        return 0;
    }

    public void setReview(){// Оставить отзыв и оценку

    }

    public void cancelOrder(){// Отменить заказ

    }
}
