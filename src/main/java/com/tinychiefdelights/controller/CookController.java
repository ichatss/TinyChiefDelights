package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Role;
import com.tinychiefdelights.repository.CookRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Работа с Поваром", tags = {"Повар"})
@RestController
public class CookController {

    private final CookRepository cookRepository;

    public CookController(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    // Aggregate Root
    @GetMapping("/cooks")
    List<Cook> all(){
        return cookRepository.findByUserRole("cook");
    }

    @PostMapping("/cooks")
    Cook newCook(@RequestBody Cook newCook){
        return cookRepository.save(newCook);
    }

    //Single Item
    @GetMapping("/cooks/{id}")
    Cook one(@PathVariable Long id) {
        return cookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/cooks/{id}")
    Cook replaceCook(@RequestBody Cook newCook, @PathVariable Long id){
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

    @DeleteMapping("/cooks/{id}")
    void deleteCook(@PathVariable Long id){
        cookRepository.deleteById(id);
    }

}