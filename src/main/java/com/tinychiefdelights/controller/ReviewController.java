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

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Aggregate Root
    @GetMapping("/reviews")
    List<Review> all(){
        return reviewRepository.findAll();
    }

    @PostMapping("/reviews")
    Review newReview(@RequestBody Review newReview){
        return reviewRepository.save(newReview);
    }

    //Single Item
    @GetMapping("/reviews/{id}")
    Review one(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/reviews/{id}")
    Review replaceReview(@RequestBody Review newReview, @PathVariable Long id){
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

    @DeleteMapping("/reviews/{id}")
    void deleteReview(@PathVariable Long id){
        reviewRepository.deleteById(id);
    }

}