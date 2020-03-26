package com.tinychiefdelights.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Admin extends User {

    public Admin(){ // Пустой конструктор для Hibernate

    }

    public Admin(String name, String lastName, String login, String password){ // Вызываем родительский конструктор
        super(name, lastName, login, password);
    }

    // Поля
    private @Id @GeneratedValue Long id;

    // Поля name, lastName, login, password наследуются от класса User;
}