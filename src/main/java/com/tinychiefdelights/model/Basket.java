package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "basket", schema = "public")
public class Basket {

    public Basket() { // Пустой конструктор для Hibernate

    }


    // Поля
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Relationships
    //
    // Лист блюд
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "basket_dish",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @JsonIgnore // Таким образом я предотвратил рекурсию
    private List<Dish> dishList;
}