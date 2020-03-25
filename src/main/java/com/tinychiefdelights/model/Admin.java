package com.tinychiefdelights.model;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Setter
@Entity
//@Table(name = "ADMIN")
public class Admin extends User {

    public Admin(){ // Пустой конструктор для Hibernate

    }

    public Admin(String name, String lastName){
        super(name, lastName);
    }

    // Поля
    private @Id @GeneratedValue Long id;


    // Методы
    public List<Order> getCurrentOrders(){ // Вывод списка всех заказов
        return null;
    }

    public Order getOrderInfo(){ // Вывод информации по конкретному заказу
        return null;
    }

    public void removeCook(){ // Удалить повара

    }

    public void editCook(){ // Изменить карту повара

    }
}