package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> { // Интерфейс для репозитория Customer

    List<Customer> findByUserRole(String role);

    Optional<Customer> findById(Long id);

    Customer getByIdAndUserRole(Long id, String role);
}
