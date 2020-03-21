package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> { // Интерфейс для репозитория Review
}
