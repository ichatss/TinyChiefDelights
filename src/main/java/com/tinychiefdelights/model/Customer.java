package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer extends User {

    public Customer(){ // Пустой конструктор для Hibernate

    }

    public Customer(String name, String lastName){ // Пользовательский конструктор создается,
        super(name, lastName);                     // когда нам нужно создать новый экземпляр,
    }                                              // но у нас еще нет ID.


    // Поля
    private @Id @GeneratedValue Long id;

    private double wallet;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orderList;


    // Методы
    public void depositMoney(){// Внести деньги на счет

        }

    public void withdrawMoney(){// Вывести деньги со счета

        }

    public void makeOrder(){// Сделать заказ

        }
}