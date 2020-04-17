package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Cook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CookRepository extends JpaRepository<Cook, Long> { // Интерфейс для репозитория Cook

    List<Cook> findByUserRole(String role); // Фильтр из БД для Повара

    Long getCookById(Long id); // Берем COOK через ID

    void deleteByUserRoleAndId(String role, Long id);

    List<Cook> findByUserRoleAndId(String role, Long id); // Фильтр из БД для Администратора ID + Role


}