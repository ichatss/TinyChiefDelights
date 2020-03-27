//package com.tinychiefdelights.model;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@Entity
//public class Customer extends User {
//
//    public Customer(){ // Пустой конструктор для Hibernate
//
//    }
//
//    public Customer(String name, String lastName, String role, String login,
//                    String password, double wallet, List<Order> orderList){ // Вызываем родительский конструктор вместе со своими данными
//
//        super(name, lastName, role, login, password);
//        this.wallet = wallet;
//        this.orderList = orderList;
//    }
//
//
//    // Поля
//    private @Id @GeneratedValue Long id;
//
//    private double wallet;
//
//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Order> orderList;
//
//    // Поля name, lastName, login, password наследуются от класса User;
//}