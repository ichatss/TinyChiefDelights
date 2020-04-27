package com.tinychiefdelights.controller;

import com.tinychiefdelights.repository.DishRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;



@Api(value = "Работа с Блюдом", tags = {"Блюдо"})
@RestController
public class DishController {

    // Constructor
    //
    // Injects are here
    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    // Fields
    //
    private final DishRepository dishRepository;


    // GET MAPPING
    //



    // POST MAPPING
    //


    // PUT MAPPING
    //


    // DELETE MAPPING
    //

}