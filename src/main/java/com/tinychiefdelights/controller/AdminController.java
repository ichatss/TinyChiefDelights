package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.model.User;
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
    List<Cook> getAllCooks() {
        return adminService.getAllCooks();
    }


    // Вывод Повара по ID
    @GetMapping("admin/cook/{id}")
    Cook getCook(@PathVariable Long id){
        return adminService.getCook(id);
    }


    // Вывод всех пользователей
    @GetMapping("admin/customers")
    List<Customer> getAllCustomer(){
        return adminService.getAllCustomers();
    }


    // Вывод Заказчика по ID
    @GetMapping("admin/customer/{id}")
    Customer getCustomer(@PathVariable Long id){
        return adminService.getCustomer(id);
    }


    // POST MAPPING
    //


    // PUT MAPPING
    //
    // Изменяем Повара по ID
    @PutMapping("/admin/edit/cook/{id}")
    void editCook(@PathVariable Long id, User user, @PathVariable float rating, String aboutCook){
        adminService.editCook(id, user, rating, aboutCook);
    }


    // DELETE MAPPING
    //
    // Удалить конкретного Повара по ID
    @DeleteMapping("/admin/cooks/delete/{id}")
    void removeCook(@PathVariable Long id) {
        adminService.deleteCook(id);
    }
}