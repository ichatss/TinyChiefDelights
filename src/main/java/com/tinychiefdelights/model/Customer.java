package com.tinychiefdelights.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

//    private List<Order> orderList; Выдает ошибку, надо потом глянуть в чем дело


    // Методы
    public void depositMoney(){// Внести деньги на счет

        }

    public void withdrawMoney(){// Вывести деньги со счета

        }

    public void makeOrder(){// Сделать заказ

        }
}