package com.tinychiefdelights.service;

import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.User;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService extends UserService {

    // Поля
    //
    // Injects in setters
    private CustomerRepository customerRepository;

    private UserRepository userRepository;


    // SETTERS
    //
    // Injects into Setters
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    // Методы
    //
    // Добавить нового Заказчика
    public Customer addCustomer(User newUser, Customer newCustomer) {
        newCustomer.setUser(newUser);
        return customerRepository.save(newCustomer);
    }


    public void depositMoney() { // Внести деньги на счет ()

    }


    // Вывести деньги со счета
    public void withdrawMoney(Long id, double money) {
        Customer customer = customerRepository.getByIdAndUserRole(id, "customer");
        if (money <= customer.getWallet()) { // Делаем проверку, чтобы сумма указанная заказчиком была меньше кошелька
            customer.setWallet(customer.getWallet() - money);
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("Введенная Вами сумма превышает остаток на счете!"); // Потом посмотрю, что делать с ошибкой
        }
    }


    public void makeOrder() { // Сделать заказ ()

    }
}