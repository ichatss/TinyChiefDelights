package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Cook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CookRepository extends JpaRepository<Cook, Long> {

    List<Cook> findByUserRole(String role);
    // Интерфейс для репозитория Cook
}