package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "Работа с Заказчиком", tags = {"Заказчик"})
@RestController
public class CustomerController {

    //Constructor
    //
    // Injects через конструктор
    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }


    // Fields
    // Injects into constructor
    //
    private final CustomerRepository customerRepository;

    private final CustomerService customerService;


    // GET MAPPING
    //
    // Вывод всех Заказчиков, если role == customer
    @GetMapping("/customers")
    List<Customer> all() {
        return customerRepository.findByUserRole("customer");
    }


    // Вывод заказчика по конкретному ID
    @GetMapping("/customers/{id}")
    Customer one(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }


    // POST MAPPING
    //
    // Создание нового заказчика
    @PostMapping("/customers")
    Customer addCustomer(User newUser, @RequestBody Customer newCustomer) {
        return customerService.addCustomer(newUser, newCustomer);
    }


    // PUT MAPPING
    //
    // Измененить конкретного заказчика по ID
    @PutMapping("/customers/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setUser(newCustomer.getUser());
                    customer.setWallet(newCustomer.getWallet());
                    customer.setOrderList(newCustomer.getOrderList());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }


    // Снять деньги со своего депозита (Заказчик)
    @PutMapping("/customer/{id}/withdraw/{money}")
    void withdrawMoney(@PathVariable Long id, @RequestParam double money) {
        customerService.withdrawMoney(id, money);
    }


    // DELETE MAPPING
    //
    // Удалить конкретного заказчика по ID !!!!!!!!!!!ДОДЕЛАТЬ
    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}