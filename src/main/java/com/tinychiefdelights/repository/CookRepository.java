package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CookRepository extends JpaRepository<Cook, Long> { // Интерфейс для репозитория Cook

    // Фильтр из БД для Повара по Role
    List<Cook> findByUserRole(Role role);

    // Берем COOK через ID
    Long getCookById(Long id);

    // Удалить Повара Role + ID
    void deleteByUserRoleAndId(Role role, Long id);

    // Фильтр из БД для Администратора ID + Role
    List<Cook> findByUserRoleAndId(Role role, Long id);

    // Берем Повара ID + ROLE
    Cook getByIdAndUserRole(Long id, Role role);

    List<Cook> getCookByIdList(Long id);

}