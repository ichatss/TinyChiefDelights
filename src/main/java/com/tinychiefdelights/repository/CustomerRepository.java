package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { // Интерфейс для репозитория Customer
}
