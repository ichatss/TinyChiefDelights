package com.tinychiefdelights.model;

import java.util.List;

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

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}