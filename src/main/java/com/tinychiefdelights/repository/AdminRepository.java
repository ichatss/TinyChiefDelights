package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long> { // Интерфейс для репозитория Admin

    Admin findByIdAndUserRole(Long id, String role);

    Admin findByUserLogin(String login);
}