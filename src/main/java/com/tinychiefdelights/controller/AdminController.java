package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.MainNotFound;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.AdminRepository;
import com.tinychiefdelights.service.AdminService;
import com.tinychiefdelights.service.CustomerService;
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
    public AdminController(AdminRepository adminRepository, AdminService adminService,
                           UserService userService, CustomerService customerService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
        this.userService = userService;
        this.customerService = customerService;
    }


    // Поля
    // All injects into constructor
    private final AdminRepository adminRepository;

    private final AdminService adminService;

    private final UserService userService;

    private final CustomerService customerService;


    // Методы
    //
    // GET MAPPING
    //
    // Показать меню
    @GetMapping("/menu")
    List<Dish> getMenu(){
        return customerService.getMenu();
    }

    @GetMapping("/dish/{id}")
    Dish getDish(@PathVariable Long id){
        return adminService.getDish(id).orElseThrow(() -> new MainNotFound(id));
    }

    // Вывод списка всех заказов
    @GetMapping("/orders")
    List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }


    // Вывод информации по заказу по ID
    @GetMapping("/order/{id}")
    Order getOrderInfo(@PathVariable Long id) {
        return adminService.getOrderInfo(id).orElseThrow(() -> new MainNotFound(id));
    }


    // Вывод всех Поваров
    @GetMapping("/cooks")
    List<Cook> getAllCooks() {
        return adminService.getAllCooks();
    }


    // Вывод Повара по ID
    @GetMapping("/cook/{id}")
    Cook getCook(@PathVariable Long id) {
        return adminService.getCook(id).orElseThrow(() -> new MainNotFound(id));
    }


    // Вывод всех пользователей
    @GetMapping("/customers")
    List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }


    // Вывод Заказчика по ID
    @GetMapping("/customer/{id}")
    Customer getCustomer(@PathVariable Long id) {
        return adminService.getCustomer(id).orElseThrow(() -> new MainNotFound(id));
    }


    // POST MAPPING
    //


    // PUT MAPPING
    //
    // Изменяем Повара по ID
    @PutMapping("/edit/cook/{id}")
    void editCook(@PathVariable Long id, String name,
                  String lastName, float rating, String aboutCook) {

        adminService.editCook(id, name, lastName, rating, aboutCook);
    }


    // Поменять пароль
    @PutMapping("/change/password")
    User changePassword(@RequestParam String login, @RequestParam String newPass) {

        return userService.changePassword(login, newPass);
    }


    // Поменять свои данные
    @PutMapping("/edit")
    Admin editAdmin(@RequestParam String login,
                    @RequestParam String name, @RequestParam String lastName) {

        return adminService.editAdmin(login, name, lastName);
    }


    // DELETE MAPPING
    //
    // Удалить Повара по ID
    @DeleteMapping("/delete/cook/{id}")
    void removeCook(@PathVariable Long id) {
        adminService.deleteCook(id);
    }
}