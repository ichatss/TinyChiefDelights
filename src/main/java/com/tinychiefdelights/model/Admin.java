package com.tinychiefdelights.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin", schema = "public")
public class Admin {

    public Admin() { // Пустой конструктор для Hibernate

    }

    // Поля

    // name, lastName, login, password берем от класса User через связи;

    private @Id
    @GeneratedValue
    Long id;


    // Relationships
    //
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Join without Admin in User class
    private User user;
}