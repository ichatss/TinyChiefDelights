package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> { // Интерфейс для репозитория Admin

    List<Admin> findByUserRole(String role); // Фильтр из БД для Администратора

}