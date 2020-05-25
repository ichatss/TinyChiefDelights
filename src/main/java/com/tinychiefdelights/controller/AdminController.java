package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
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
    List<Dish> getMenu() {
        return customerService.getMenu();
    }


    // Показать блюдо по ИД
    @GetMapping("/dish/{id}")
    Dish getDish(@PathVariable Long id) {
        return adminService.getDish(id);
    }


    // Вывод списка всех заказов
    @GetMapping("/orders")
    List<Order> getAllOrders() {
        return adminService.getAllOrders();
    }


    // Вывод информации по заказу по ID
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
    List<Customer> getAllCustomers() {
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
    // Создаем повара

    // Изменяем Повара по ID
    @PutMapping("/edit/cook/{id}")
    void editCook(@PathVariable Long id, @RequestParam String name, @RequestParam float startSalary,
                  @RequestParam String lastName, @RequestParam float rating,
                  @RequestParam String aboutCook, @RequestParam CookType cookType) {

        // Валидация
        if (name.length() <= 1 || name.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (lastName.length() <= 1 || lastName.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (rating >= 5 || rating < 1) {
            throw new MainIllegalArgument("Рейтинг повара должен быть не более 4.99 и не менее 1!");
        }
        if (aboutCook.length() <= 15) {
            throw new MainIllegalArgument("Минимальное количество символов должно быть 15");
        }
        if (startSalary < 100 && startSalary > 5000) {
            throw new MainIllegalArgument("Минимальное значение 100, максимальное 5000");
        }
        //

        adminService.editCook(id, name, lastName, rating, aboutCook, cookType, startSalary);
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

        // Валидация
        if (name.length() <= 1 || name.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (lastName.length() <= 1 || lastName.length() >= 27) {
            throw new MainIllegalArgument("Имя должно быть не менее 2 символов и не более 26!");
        }
        if (login.length() <= 3 || login.length() >= 23) {
            throw new MainIllegalArgument("Логин должен быть не менее 4 символов и не более 24!");
        }
        //

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