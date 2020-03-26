package com.tinychiefdelights.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class User {

    public User(){ // Пустой конструктор для Hibernate

    }

    public User(String name, String lastName, String login, String password, String role){ // Базовый конструктор для дочерних классов
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    // Поля
    private String login;

    private String password;

    private String role;

    private String name;

    private String lastName;
}