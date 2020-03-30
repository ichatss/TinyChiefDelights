package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Admin;
import com.tinychiefdelights.repository.AdminRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Aggregate Root
    @GetMapping("/admins")
    List<Admin> all(){
        return adminRepository.findAll();
    }

    @PostMapping("/admins")
    Admin newAdmin(@RequestBody Admin newAdmin){
        return adminRepository.save(newAdmin);
    }

    //Single Item
    @GetMapping("/admins/{id}")
    Admin one(@PathVariable Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/admins/{id}")
    Admin replaceAdmin(@RequestBody Admin newAdmin, @PathVariable Long id){
        return adminRepository.findById(id)
                .map(admin -> {
                    admin.setName(newAdmin.getName());
                    admin.setLastName(newAdmin.getLastName());
                    return adminRepository.save(admin);
                })
                .orElseGet(() -> {
                    newAdmin.setId(id);
                    return adminRepository.save(newAdmin);
                });
    }

    @DeleteMapping("/admins/{id}")
    void deleteAdmins(@PathVariable Long id){
        adminRepository.deleteById(id);
    }
}