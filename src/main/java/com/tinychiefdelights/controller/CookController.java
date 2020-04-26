package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.repository.CookRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    // Вывод всех Поваров, если role == cook
    @GetMapping("/cooks")
    List<Cook> all() {
        return cookRepository.findByUserRole("cook");
    }


    // Вывод конкретного Повара по ID
    @GetMapping("/cooks/{id}")
    Cook one(@PathVariable Long id) {
        return cookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    // POST MAPPING
    //
    // Создание нового Повара !!!!!!!!!!!!!!!!!!!!!!ДОДЕЛАТЬ
    @PostMapping("/cooks")
    Cook newCook(@RequestBody Cook newCook) {
        return cookRepository.save(newCook);
    }


    // PUT MAPPING
    //
    // Изменить Повара
    @PutMapping("/cooks/{id}")
    Cook replaceCook(@RequestBody Cook newCook, @PathVariable Long id) {
        return cookRepository.findById(id)
                .map(cook -> {
                    cook.setUser(newCook.getUser());
                    cook.setRating(newCook.getRating());
                    cook.setCookStatus(newCook.isCookStatus());
                    cook.setReviewList(newCook.getReviewList());
                    cook.setAboutCook(newCook.getAboutCook());
                    return cookRepository.save(cook);
                })
                .orElseGet(() -> {
                    newCook.setId(id);
                    return cookRepository.save(newCook);
                });
    }


    // DELETE MAPPING
    //
    // Ужадить Повара по ID !!!!!!!!!!!!!! DODELAT
    @DeleteMapping("/cooks/{id}")
    void deleteCook(@PathVariable Long id) {
        cookRepository.deleteById(id);
    }
}