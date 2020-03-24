package com.tinychiefdelights.model;

import lombok.Setter;
import org.hibernate.annotations.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Setter
@Entity
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