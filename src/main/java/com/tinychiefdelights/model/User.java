package com.tinychiefdelights.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel
@Data
@Entity
@Table(name = "pg_user", schema = "public")
public class User {

    public User() { // Пустой конструктор для Hibernate

    }

    public User(String name, String lastName, String role,
                String login, String password) { // Базовый конструктор

        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.login = login;
        this.password = password;
    }


    // Поля
    @ApiModelProperty
    private @Id
    @GeneratedValue
    Long id;

    @ApiModelProperty
    @Column(name = "login")
    private String login;

    @ApiModelProperty
    @Column(name = "password")
    private String password;

    @ApiModelProperty
    @Column(name = "role")
    private String role;

    @ApiModelProperty
    @Column(name = "name")
    private String name;

    @ApiModelProperty
    @Column(name = "last_name")
    private String lastName;
}