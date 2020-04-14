package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer", schema = "public")
public class Customer {

    public Customer() { // Пустой конструктор для Hibernate

    }


    // Поля

    // name, lastName, login, password берем от класса User через связи;

    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "wallet")
    private double wallet;


    //Relationships
    //
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Join without Customer in User class
    private User user;

    //Лист заказов
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference // Таким образом я предотвратил рекурсию
    private List<Order> orderList;

}