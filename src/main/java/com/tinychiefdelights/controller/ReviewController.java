package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Review;
import com.tinychiefdelights.repository.ReviewRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Работа с Отзывами", tags = {"Отзыв"})
@RestController
public class ReviewController {

    // Constructor
    //
    // Injects are here
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    // FIELDS
    private final ReviewRepository reviewRepository;


    // GET MAPPING
    //
    // Вывод всех Отзывов
    @GetMapping("/reviews")
    List<Review> all() {
        return reviewRepository.findAll();
    }


    // Вывод Заказа по ID
    @GetMapping("/reviews/{id}")
    Review one(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    // POST MAPPING
    //
    // Создать новый Отзыв
    @PostMapping("/reviews")
    Review newReview(@RequestBody Review newReview) {
        return reviewRepository.save(newReview);
    }


    // PUT MAPPING
    //
    // Изменить Отзыв по ID
    @PutMapping("/reviews/{id}")
    Review replaceReview(@RequestBody Review newReview, @PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setReview(newReview.getReview());
                    review.setRate(newReview.getRate());
                    return reviewRepository.save(review);
                })
                .orElseGet(() -> {
                    newReview.setId(id);
                    return reviewRepository.save(newReview);
                });
    }


    // DELETE MAPPING
    //
    // Удалить заказ по ID !!!!!!!!!!!!!!!!! ДОДЕЛАТЬ
    @DeleteMapping("/reviews/{id}")
    void deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
    }
}