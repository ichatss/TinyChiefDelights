package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer", schema = "public")
public class Customer {

    public Customer(){ // Пустой конструктор для Hibernate

    }

    // Поля
    private @Id @GeneratedValue Long id;

    @OneToOne(mappedBy = "")
    private User user;

    @Column(name = "wallet")
    private double wallet;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_customer",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    @JsonManagedReference // Таким образом я предотвратил рекурсию
    private List<Order> orderList;

    // Поля name, lastName, login, password наследуются от класса User;
}