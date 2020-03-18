package com.tinychiefdelights.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customer extends User {

    // Поля
    private double wallet;

    private List<Order> orderList;


    // Методы
    public void depositMoney(){// Внести деньги на счет

        }

    public void withdrawMoney(){// Вывести деньги со счета

        }

    public void makeOrder(){// Сделать заказ

        }
}