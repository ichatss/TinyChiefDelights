package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "customer", schema = "public")
public class Customer {

    public Customer() { // Пустой конструктор для Hibernate

    }


    // Поля

    // name, lastName, login, password берем от класса User через связи;

    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "wallet")
    private double wallet;


    //Relationships
    //
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Join without Customer in User class
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    //Лист заказов
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Order> orderList;
}