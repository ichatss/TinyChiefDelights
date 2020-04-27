package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}