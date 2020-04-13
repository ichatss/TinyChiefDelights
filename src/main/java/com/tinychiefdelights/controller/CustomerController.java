package com.tinychiefdelights.controller;

import com.tinychiefdelights.exceptions.NotFoundException;
import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Aggregate Root
    @GetMapping("/customers")
    List<Customer> all(){
        return customerRepository.findAll();
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
}