package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> { // Интерфейс для репозитория Customer

    // Вывод Заказчиков с фильтор Role
    List<Customer> findByUserRole(String role);

    // Берем Заказчика, фильтруя через ID + Role
    Optional<Customer> getByIdAndUserRole(Long id, String role);

    // Берем Заказчика, фильтруя через ID + Role без Optional<>
    Customer findByIdAndUserRole(Long id, String role);
}