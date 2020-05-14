package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Найти пользователя по Login (логин универсален)
    User getByLogin(String login);

    Optional<User> save();
}