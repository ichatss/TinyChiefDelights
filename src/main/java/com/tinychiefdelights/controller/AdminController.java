package com.tinychiefdelights.controller;

import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.AdminRepository;
import com.tinychiefdelights.service.AdminService;
import com.tinychiefdelights.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static com.tinychiefdelights.model.User.ROLE_ADMIN;


@Api(value = "Работа с Админом", tags = {"Администратор"})
@RestController
@RequestMapping("/admin")
@RolesAllowed({ROLE_ADMIN})
public class AdminController {

    // Constructor
    //
    // Inject через конструктор
    @Autowired
    public AdminController(AdminRepository adminRepository, AdminService adminService, UserService userService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
        this.userService = userService;
    }


    // Поля
    // All injects into constructor
    private final AdminRepository adminRepository;

    private final AdminService adminService;

    private final UserService userService;


    // Методы
    //
    // GET MAPPING
    //
    // Вывод списка всех заказов
    @GetMapping("/orders")
    List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }


    // Вывод информации по конкретному заказу по ID
    @GetMapping("/order/{id}")
    Order getOrderInfo(@PathVariable Long id) {
        return adminService.getOrderInfo(id);
    }


    // Вывод всех Поваров
    @GetMapping("/cooks")
    List<Cook> getAllCooks() {
        return adminService.getAllCooks();
    }


    // Вывод Повара по ID
    @GetMapping("/cook/{id}")
    Cook getCook(@PathVariable Long id) {
        return adminService.getCook(id);
    }


    // Вывод всех пользователей
    @GetMapping("/customers")
    List<Customer> getAllCustomer() {
        return adminService.getAllCustomers();
    }


    // Вывод Заказчика по ID
    @GetMapping("/customer/{id}")
    Customer getCustomer(@PathVariable Long id) {
        return adminService.getCustomer(id);
    }


    // POST MAPPING
    //


    // PUT MAPPING
    //
    // Изменяем Повара по ID
    @PutMapping("/edit/cook/{id}")
    void editCook(@PathVariable Long id, User user, float rating, String aboutCook) {
        adminService.editCook(id, user, rating, aboutCook);
    }

    // Поменять пароль
    @PutMapping("/change/password")
    void changePassword(@RequestParam String login, @RequestParam String newPass) {
        userService.changePassword(login, newPass);
    }


    // DELETE MAPPING
    //
    // Удалить конкретного Повара по ID
    @DeleteMapping("/delete/cook/{id}")
    void removeCook(@PathVariable Long id) {
        adminService.deleteCook(id);
    }
}