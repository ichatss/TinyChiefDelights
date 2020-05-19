package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Basket getById(Long id);

}
