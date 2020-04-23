package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Admin;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.repository.AdminRepository;
import com.tinychiefdelights.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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



    // Методы
    @GetMapping("/admins")
    List<Admin> all(){ // Ищем в базе только тех, у кого role == cook
        return adminRepository.findByUserRole("admin");
    }


    @PostMapping("/admins")
    Admin newAdmin(@RequestBody Admin newAdmin){
        return adminRepository.save(newAdmin);
    }


    @GetMapping("/admins/{id}") // Вывод админов по конкретному ID
    Admin one(@PathVariable Long id) {
        return adminRepository.findByUserRoleAndId("admin", id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    @PutMapping("/admins/{id}")
    Admin replaceAdmin(@RequestBody Admin newAdmin, @PathVariable Long id){
        return adminRepository.findByUserRoleAndId("admin", id)
                .map(admin -> {
                    admin.setUser(newAdmin.getUser());
                    return adminRepository.save(admin);
                })
                .orElseGet(() -> {
                    newAdmin.setId(id);
                    return adminRepository.save(newAdmin);
                });
    }


    @DeleteMapping("/admins/{id}") // Удалить админа по конкретному ID
    void deleteAdmins(@PathVariable Long id){
        adminRepository.deleteByUserRoleAndId("admin", id);
    }


    @GetMapping("admin/orders")
    List<Order> getAllOrders(){ // Вывод списка всех заказов
        return adminService.getAllOrders();
    }


    @GetMapping("admin/orders/{id}")
    Order getOrderInfo(@PathVariable Long id){ // Вывод информации по конкретному заказу по ID
        return adminService.getOrderInfo(id);
    }


    @GetMapping("admin/cooks")
    List<Cook> getAllCooks(@PathVariable Long id){ // Вывод всех поваров
        return adminService.getAllCooks(id);
    }


    @DeleteMapping("/admin/cooks/delete/{id}")
    void removeCook(@PathVariable Long id){ // Удалить конкретного повара по ID
        adminService.removeCook(id);
    }
}