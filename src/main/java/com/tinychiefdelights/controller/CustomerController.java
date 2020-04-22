package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Customer;
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
    Customer newCustomer(@RequestBody Customer newCustomer){
        return customerRepository.save(newCustomer);
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


    @PutMapping("/customer/withdraw/{id}") // ДОДЕЛАТЬ НАДО!!!!!!!!!!
    void withdrawMoney(@RequestBody Customer customer, @PathVariable Long id, @PathVariable double money){
        customerService.withdrawMoney(customer, money);
    }
}