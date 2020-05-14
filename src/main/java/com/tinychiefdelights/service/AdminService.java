package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Методы
    //
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
    public void editCook(Long id, User user, float rating, String aboutCook) {
        Cook cook = cookRepository.findByIdAndUserRole(id, "COOK");
        try {
            cook.setUser(user);
            cook.setRating(rating);
            cook.setAboutCook(aboutCook);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (NotFoundException e) {
            throw new NotFoundException(id);
        }
    }


    // Вывод всех поваров
    public List<Cook> getAllCooks() {
        return cookRepository.findByUserRole("COOK");
    }


    // Удалить Повара
    public void deleteCook(Long id) {
        Cook cook = cookRepository.findByIdAndUserRole(id, "COOK");
        try {
            cookRepository.delete(cook);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
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