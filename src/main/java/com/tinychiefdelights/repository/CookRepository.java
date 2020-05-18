package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Cook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CookRepository extends JpaRepository<Cook, Long> { // Интерфейс для репозитория Cook

    // Фильтр из БД для Повара по Role
    List<Cook> findByUserRole(String role);

    // Берем COOK через ID
    Cook getCookById(Long id);

    // Удалить Повара Role + ID
    void deleteByUserRoleAndId(String role, Long id);

    // Фильтр из БД для Администратора ID + Role
    List<Cook> findByUserRoleAndId(String role, Long id);

    // Берем Повара ID + ROLE
    Optional<Cook> getByIdAndUserRole(Long id, String role);

    // Берем Повара ID + ROLE только без Optional<>
    Cook findByIdAndUserRole(Long id, String role);


}