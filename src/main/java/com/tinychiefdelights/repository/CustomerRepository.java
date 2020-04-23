package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> { // Интерфейс для репозитория Customer

    List<Customer> findByUserRole(String role);

//    void depositMoney(double money);

//    void withdrawMoney(Customer customer, double money);

}
