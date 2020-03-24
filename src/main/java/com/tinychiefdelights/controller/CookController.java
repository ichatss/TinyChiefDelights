package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.repository.CookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CookController {

    private final CookRepository cookRepository;

    public CookController(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    // Aggregate Root
    @GetMapping("/cooks")
    List<Cook> all(){
        return cookRepository.findAll();
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
                    cook.setName(newCook.getName());
                    cook.setLastName(newCook.getLastName());
                    cook.setRating(newCook.getRating());
                    cook.isCookStatus();
                    return cookRepository.save(cook);
                })
                .orElseGet(() -> {
                    newCook.setId(id);
                    return cookRepository.save(newCook);
                });
    }

    @DeleteMapping("/cooks/{id}")
    void deleteCooks(@PathVariable Long id){
        cookRepository.deleteById(id);
    }
}