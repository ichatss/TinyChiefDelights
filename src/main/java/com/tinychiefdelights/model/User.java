package com.tinychiefdelights.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // позволяет включать класс и его jpa аннотации в производный класс,
                    // не делая базовый класс сущностью.
@Table(name = "pg_user", schema = "public")
public class User {

    public User(){ // Пустой конструктор для Hibernate

    }

    public User(String name, String lastName, String role,
                String login, String password){ // Базовый конструктор для дочерних классов

        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    // Поля
    private @Id @GeneratedValue Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
}