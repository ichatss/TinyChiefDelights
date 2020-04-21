package com.tinychiefdelights.service;

import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Order;
import com.tinychiefdelights.repository.AdminRepository;
import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends UserService {

    // Поля
    //
    private AdminRepository adminRepository; // Администратор

    private OrderRepository orderRepository; // Заказ

    private CookRepository cookRepository; // Повар


    // Getters and Setters
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
    public List<Order> getAllOrders(){ // Вывод списка всех заказов
        return orderRepository.findAll();
    }

    public Order getOrderInfo(Long id){ // Вывод информации по конкретному заказу
        return orderRepository.getById(id);
    }

    public void removeCook(Long id){ // Удалить повара
        cookRepository.deleteByUserRoleAndId("cook", id); // Узнать у Зураба, что сделать с этим!!!
    }

    public void editCook(){ // Изменить карту повара ()

    }

    public List<Cook> getAllCooks(Long id){ // Вывод всех поваров
        return cookRepository.findByUserRoleAndId("cook", id);
    }

}