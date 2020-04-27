package com.tinychiefdelights.controller;

import com.tinychiefdelights.repository.ReviewRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;



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


    // POST MAPPING
    //


    // PUT MAPPING
    //


    // DELETE MAPPING
    //

}