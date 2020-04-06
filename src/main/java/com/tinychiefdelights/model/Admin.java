package com.tinychiefdelights.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pg_user", schema = "public")
public class Admin extends User {

    public Admin(){ // Пустой конструктор для Hibernate

    }

    public Admin(String name, String lastName, String role, String login, String password) { // Вызываем родительский конструктор
        super(name, lastName, role, login, password);
    }

    // Поля
    private @Id @GeneratedValue Long id;

//    @OneToOne(mappedBy = "pg_user")
//    private User user;

    // Поля name, lastName, role, login, password наследуются от класса User;
}