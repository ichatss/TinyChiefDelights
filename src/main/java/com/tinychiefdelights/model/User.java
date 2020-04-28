package com.tinychiefdelights.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ApiModel
@Data
@Entity
@Table(name = "pg_user", schema = "public")
public class User {

    public User() { // Пустой конструктор для Hibernate

    }

    public User(String name, String lastName, Role role,
                String login, String password) { // Базовый конструктор

        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.login = login;
        this.password = password;
    }


    // Поля
    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "login")
    private String login;

    @Size(min = 5, max = 30)
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;
}