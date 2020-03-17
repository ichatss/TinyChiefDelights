package com.tinychiefdelights.model;

import java.util.Date;
import java.util.List;

public class Order {

    short orderNumber;
    Customer customer;
    String adress;
    String phoneNumber;
    Date dateOrder;
    Cook cook;
    List<Dish> dishList;
    enum OrderStatus{}

    double calculateCost(){return 0;}
    void setReview(){}
    void cancelOrder(){}

}
