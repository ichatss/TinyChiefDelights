package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CookRepository extends JpaRepository<Cook, Long> { // Интерфейс для репозитория Cook

    List<Cook> findByUserRole(String role);
}