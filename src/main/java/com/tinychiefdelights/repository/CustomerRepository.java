package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> { // Интерфейс для репозитория Customer

    // Вывод Заказчиков с фильтор Role
    List<Customer> findByUserRole(String role);

    // Найти Заказчика по ID
    Optional<Customer> findById(Long id);

    // Берем Заказчика, фильтруя через ID + Role
    Customer getByIdAndUserRole(Long id, String role);
}