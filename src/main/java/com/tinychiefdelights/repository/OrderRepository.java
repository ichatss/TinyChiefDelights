package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> { // Интерфейс для репозитория Order

    Order getById(Long id); // Берем ORDER через ID

}
