package com.tinychiefdelights.service;

import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends UserService {


    private CustomerRepository customerRepository;


    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    // Методы
    //
    public void depositMoney(){ // Внести деньги на счет ()

    }

    public void withdrawMoney(Customer customer, double money){ // Вывести деньги со счета
        if (money <= customer.getWallet()) { // Делаем проверку, чтобы сумма указанная заказчиком была меньше кошелька
            customer.setWallet(customer.getWallet() - money);
        } else {
            throw new RuntimeException("Введенная Вами сумма превышает остаток на счете!");

        }
    }

    public void makeOrder(){ // Сделать заказ ()

    }
}
