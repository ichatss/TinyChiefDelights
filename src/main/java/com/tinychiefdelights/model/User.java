package com.tinychiefdelights.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    public User(){ // Пустой конструктор для Hibernate

    }

    User(String name, String lastName){ // Базовый конструктор для дочерних классов
        this.name = name;
        this.lastName = lastName;
    }

    // Поля
    private String login;

    private String password;

    private String name;

    private String lastName;

    @Override
    public String toString() { // Без этого метода вместо инициализированных строк выводятся символы.
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    // Методы
    public void changePassword(){// Сменить пароль

    }
}