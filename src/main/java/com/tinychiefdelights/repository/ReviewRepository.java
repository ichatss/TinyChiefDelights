package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> { // Интерфейс для репозитория Review

    Review findReviewByOrderId(Long id);

}