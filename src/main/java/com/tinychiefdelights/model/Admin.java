package com.tinychiefdelights.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pg_user", schema = "public")
public class Admin {

    public Admin(){ // Пустой конструктор для Hibernate

    }

    public Admin(User user){ // Вызываем родительский конструктор
        this.user = user;
    }

    // Поля
    private @Id @GeneratedValue Long id;

    @OneToOne(mappedBy = "admin")
    private User user;

    // Поля name, lastName, role, login, password наследуются от класса User;
}