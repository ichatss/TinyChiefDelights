package com.tinychiefdelights.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel
@Data
@Entity
@Table(name = "pg_user", schema = "public")
public class Admin {

    public Admin() { // Пустой конструктор для Hibernate

    }

    // Поля

    // name, lastName, login, password берем от класса User через связи;

    @ApiModelProperty
    private @Id
    @GeneratedValue
    Long id;


    // Relationships
    //
    @ApiModelProperty
    @OneToOne
    @JoinColumn(name = "id") // Join without Admin in User class
    private User user;

}