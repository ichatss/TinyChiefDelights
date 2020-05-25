package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.MainIllegalArgument;
import com.tinychiefdelights.exceptions.MainNotFound;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private DishRepository dishRepository; // Блюдо


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

    @Autowired
    public void setDishRepository(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Autowired
    @Override
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Методы
    //
    // Вывести блюдо по ID (метод есть только у админа)
    public Dish getDish(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new MainNotFound(id));
    }


    // Вывод Заказчика по ID
    public Customer getCustomer(Long id) {
        return customerRepository.getByIdAndUserRole(id, User.ROLE_CUSTOMER).orElseThrow(() -> new MainNotFound(id));
    }


    // Вывод всех Заказчиков
    public List<Customer> getAllCustomers() {
        return customerRepository.findByUserRole(User.ROLE_CUSTOMER);
    }


    // Вывод списка всех заказов
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    // Вывод информации по конкретному заказу
    public Order getOrderInfo(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new MainNotFound(id));
    }


    // Вывод всех поваров
    public List<Cook> getAllCooks() {
        return cookRepository.findAll();
    }


    // Вывод Повара по ID
    public Cook getCook(Long id) {
        return cookRepository.getCookAndChefById(id).orElseThrow(() -> new MainNotFound(id));
    }


    // Удалить Повара
    @Transactional
    public void deleteCook(Long id) {
        try {
            cookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new MainNotFound(id);
        }
    }


    // Изменить карту повара
    public void editCook(Long id, String name, String lastName, float rating, String aboutCook) {

        Cook cook = cookRepository.findByIdAndUserRole(id, User.ROLE_COOK);

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


    // Изменить свои данные
    public Admin editAdmin(String login, String name, String lastName) {

        Admin admin = adminRepository
                .findByIdAndUserRole(User.getCurrentUser().getId(), User.ROLE_ADMIN);

        if (!userRepository.existsByLogin(login)) {

            admin.getUser().setLogin(login);

        } else {
            throw new MainIllegalArgument("Данный логин уже занят!");
        }

        admin.getUser().setName(name);
        admin.getUser().setLastName(lastName);
        return adminRepository.save(admin);
    }
}