package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer", schema = "public")
public class Customer extends User {

    public Customer(){ // Пустой конструктор для Hibernate

    }

    public Customer(String name, String lastName, String role, String login,
                    String password, double wallet, List<Order> orderList){ // Вызываем родительский конструктор вместе со своими данными

        super(name, lastName, role, login, password);
        this.wallet = wallet;
        this.orderList = orderList;
    }


    // Поля
    private @Id @GeneratedValue Long id;

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