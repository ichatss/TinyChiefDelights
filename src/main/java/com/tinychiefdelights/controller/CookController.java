package com.tinychiefdelights.controller;

import com.tinychiefdelights.repository.CookRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;



@Api(value = "Работа с Поваром", tags = {"Повар"})
@RestController
public class CookController {

    //Injects через конструктор
    public CookController(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }


    // Поля
    //
    private final CookRepository cookRepository;


    // GET MAPPING
    //


    // POST MAPPING
    //


    // PUT MAPPING
    //


    // DELETE MAPPING
    //

}