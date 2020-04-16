package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Admin;
import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.repository.AdminRepository;
import com.tinychiefdelights.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Работа с Админом", tags = {"Администратор"})
@RestController
public class AdminController {

    // Constructor
    //
    @Autowired
    public AdminController(AdminRepository adminRepository, AdminService adminService) { // Тут inject через конструктор
        this.adminRepository = adminRepository;
        this.adminService = adminService;
    }


    // Поля
    // All injects into constructor
    private final AdminRepository adminRepository;

    private AdminService adminService;


    // Aggregate Root
    @GetMapping("/admins")
    List<Admin> all(){ // Ищем в базе только тех, у кого ENUM == role
        return adminRepository.findByUserRole("admin");
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
                    admin.setUser(newAdmin.getUser());
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


    @GetMapping("/order/{id}")
    Order getOrderInfo(Long id){
        return adminService.getOrderInfo(id);
    }

}