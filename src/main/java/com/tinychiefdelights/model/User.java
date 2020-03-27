package com.tinychiefdelights.model;

import lombok.Data;

import javax.persistence.*;

@Data
//@MappedSuperclass
@Entity
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

    private String login;

    private String password;

    private String role;

    private String name;

    @Column(name = "lastname")
    private String lastName;
}