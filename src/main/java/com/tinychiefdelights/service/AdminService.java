package com.tinychiefdelights.service;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.repository.AdminRepository;
import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends UserService {

    // Поля
    //
    // Injects in setters
    private AdminRepository adminRepository; // Администратор

    private OrderRepository orderRepository; // Заказ

    private CookRepository cookRepository; // Повар


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


    // Методы
    //
    // Вывод списка всех заказов
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    // Вывод информации по конкретному заказу
    public Order getOrderInfo(Long id) {
        try {
            return orderRepository.getById(id);
        }catch (NotFoundException e) {
            throw new NotFoundException(id);
        }
    }


    public void editCook() { // Изменить карту повара ()

    }


    // Вывод всех поваров
    public List<Cook> getAllCooks(Long id) {
        return cookRepository.findByUserRoleAndId("cook", id);
    }


    // Удалить Повара
    public void deleteCook(Long id){
        Cook cook = cookRepository.getByIdAndUserRole(id, "cook");
        try {
            cookRepository.delete(cook);
        } catch (Exception e) {
            throw new NotFoundException(id);
        }
    }
}