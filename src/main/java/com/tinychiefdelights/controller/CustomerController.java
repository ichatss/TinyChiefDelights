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
import java.util.Optional;

@Api(value = "Работа с Заказчиком", tags = {"Заказчик"})
@RestController
public class CustomerController {

    //Constructor
    //
    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }


    // Fields
    //Injects into constructor
    //
    private final CustomerRepository customerRepository;

    private CustomerService customerService;



    // Aggregate Root
    @GetMapping("/customers")
    List<Customer> all(){
        return customerRepository.findByUserRole("customer");
    }

    @PostMapping("/customers")
    Customer addCustomer(User newUser, @RequestBody Customer newCustomer){
        return customerService.addCustomer(newUser, newCustomer);
    }

    //Single Item
    @GetMapping("/customers/{id}")
    Customer one(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/customers/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id){
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

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
    }


    @PutMapping("/customer/{id}/withdraw/{money}")
    void withdrawMoney(@PathVariable Long id, @RequestParam double money){
        customerService.withdrawMoney(id, money);
    }
}