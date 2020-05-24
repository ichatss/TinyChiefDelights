package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNotFound;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends UserService {

    // Поля
    //
    // Injects in setters
    private AdminRepository adminRepository; // Администратор

    private OrderRepository orderRepository; // Заказ

    private CookRepository cookRepository; // Повар

    private CustomerRepository customerRepository; // Заказчик

    private UserRepository userRepository; // Общий пользователь

    private PasswordEncoder passwordEncoder; // для шифра пароля

    private DishRepository dishRepository; // Блюдо

    @Autowired
//    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Getters and Setters
    //
    // Делаем inject через сеттеры
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCookRepository(CookRepository cookRepository) {
        this.cookRepository = cookRepository;
    }

    //    @Override
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Методы
    //
    // Выести блюдо по ID (метод есть только у админа)
    public Optional<Dish> getDish(Long id){
        return dishRepository.findById(id);
    }


    // Вывод списка всех заказов
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    // Вывод информации по конкретному заказу
    public Optional<Order> getOrderInfo(Long id) {
        return orderRepository.findById(id);
    }


    // Вывод Повара по ID
    public Optional<Cook> getCook(Long id) {
        try {
            return cookRepository.getByIdAndUserRole(id, "COOK");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }



    // Изменить карту повара
    public void editCook(Long id, String name, String lastName, float rating, String aboutCook) {

        Cook cook = cookRepository.findByIdAndUserRole(id, "COOK");

        if (cook != null) {

            cook.getUser().setName(name);
            cook.getUser().setLastName(lastName);
            cook.setRating(rating);
            cook.setAboutCook(aboutCook);
            cookRepository.save(cook);
        } else {
            throw new MainNotFound(id);
        }
    }


    // Изменить свой данные
    public Admin editAdmin(String login, String name, String lastName) {

        Admin admin = adminRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_ADMIN);

        if (userRepository.getByLogin(login) == null) {
            admin.getUser().setLogin(login);
        } else {
            throw new MainIllegalArgument("Данный логин уже занят!");
        }
        admin.getUser().setName(name);
        admin.getUser().setLastName(lastName);
        return adminRepository.save(admin);
    }


    // Вывод всех поваров
    public List<Cook> getAllCooks() {
        return cookRepository.findByUserRole("COOK");
    }


    // Удалить Повара
    @Transactional
    public void deleteCook(Long id) {

        cookRepository.deleteByUserRoleAndId("COOK", id);

    }


    // Вывод всех Заказчиков
    public List<Customer> getAllCustomers() {
        return customerRepository.findByUserRole("CUSTOMER");
    }


    // Вывод Заказчика по ID
    public Optional<Customer> getCustomer(Long id) {
        try {
            return customerRepository.getByIdAndUserRole(id, "CUSTOMER");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}