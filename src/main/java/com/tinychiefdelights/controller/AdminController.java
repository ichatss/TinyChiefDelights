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


@Api(value = "Работа с Админом", tags = {"Администратор"})
@RestController
public class AdminController {

    // Constructor
    //
    // Inject через конструктор
    @Autowired
    public AdminController(AdminRepository adminRepository, AdminService adminService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
    }


    // Поля
    // All injects into constructor
    private final AdminRepository adminRepository;

    private final AdminService adminService;


    // Методы
    //

    // GET MAPPING
    //
    // Ищем в базе только тех, у кого role == cook
    @GetMapping("/admins")
    List<Admin> all() {
        return adminRepository.findByUserRole("admin");
    }


    // Вывод админов по конкретному ID
    @GetMapping("/admins/{id}")
    Admin one(@PathVariable Long id) {
        return adminRepository.findByUserRoleAndId("admin", id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    // Вывод списка всех заказов
    @GetMapping("admin/orders")
    List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }


    // Вывод информации по конкретному заказу по ID
    @GetMapping("admin/orders/{id}")
    Order getOrderInfo(@PathVariable Long id) {
        return adminService.getOrderInfo(id);
    }


    // Вывод всех Поваров
    @GetMapping("admin/cooks")
    List<Cook> getAllCooks(@PathVariable Long id) {
        return adminService.getAllCooks(id);
    }


    // POST MAPPING
    //
    // Создаем нового Админа !!!!!!!!!!!!ДОДЕЛАТЬ
    @PostMapping("/admins")
    Admin newAdmin(@RequestBody Admin newAdmin) {
        return adminRepository.save(newAdmin);
    }


    // PUT MAPPING
    //
    // Изменяем конкретного Админа
    @PutMapping("/admins/{id}")
    Admin replaceAdmin(@RequestBody Admin newAdmin, @PathVariable Long id) {
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


    // DELETE MAPPING
    //
    // Удалить Админа по конкретному ID !!!!!!!!! Доделать
    @DeleteMapping("/admins/{id}")
    void deleteAdmins(@PathVariable Long id) {
        adminRepository.deleteByUserRoleAndId("admin", id);
    }


    // Удалить конкретного Повара по ID
    @DeleteMapping("/admin/cooks/delete/{id}")
    void removeCook(@PathVariable Long id) {
        adminService.deleteCook(id);
    }
}