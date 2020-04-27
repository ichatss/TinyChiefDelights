package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CookRepository extends JpaRepository<Cook, Long> { // Интерфейс для репозитория Cook

    // Фильтр из БД для Повара по Role
    List<Cook> findByUserRole(String role);

    // Берем COOK через ID
    Long getCookById(Long id);

    // Удалить Повара Role + ID
    void deleteByUserRoleAndId(String role, Long id);

    // Фильтр из БД для Администратора ID + Role
    List<Cook> findByUserRoleAndId(String role, Long id);

    // Берем Повара ID + ROLE
    Cook getByIdAndUserRole(Long id, String role);

}