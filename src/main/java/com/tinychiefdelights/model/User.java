package com.tinychiefdelights.model;

import java.util.List;

public class User {

    // Поля
    private String login;

    private String password;

    private String name;

    private String lastName;

    // Методы
    public void changePassword(){// Сменить пароль

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
