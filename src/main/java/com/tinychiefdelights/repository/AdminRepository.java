package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Admin;
import com.tinychiefdelights.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> { // Интерфейс для репозитория Admin

    // Фильтр из БД для Администратора only Role
    List<Admin> findByUserRole(String Role);

    // Фильтр из БД для Администратора ID + Role
    Optional<Admin> findByUserRoleAndId(String role, Long id);

    // Удалить из БД Администратора ID + Role
    void deleteByUserRoleAndId(String role, Long id);

    Admin findByIdAndUserRole(Long id, String role);

}