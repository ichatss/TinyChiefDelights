package com.tinychiefdelights.database;

import com.tinychiefdelights.model.*;
import com.tinychiefdelights.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner Customer(CustomerRepository customerRepository) { // Добавляю данные для Customer
        return args -> {
            log.info("Preloading " + customerRepository.save(new Customer(/*"Артур", "Вартанян", 5347.56, new Order(1, */)));
            log.info("Preloading " + customerRepository.save(new Customer(/*"Андрей", "Чекмарев"*/)));
        };
    }

    @Bean
    CommandLineRunner Review(ReviewRepository reviewRepository) { // Добавляю данные для Review
        return args -> {
            log.info("Preloading " + reviewRepository.save(new Review("Выйграл лигу поваров 2015", 5.0f)));
            log.info("Preloading " + reviewRepository.save(new Review("Очень невкусно готовит", 2.4f)));
        };
    }

    @Bean
    CommandLineRunner Admin(AdminRepository adminRepository) { // Добавляю данные для Admin
        return args -> {
            log.info("Preloading " + adminRepository.save(new Admin("Илья", "Столяров")));
            log.info("Preloading " + adminRepository.save(new Admin("Иван", "Петросян")));
        };
    }

    @Bean
    CommandLineRunner Cook(CookRepository cookRepository) { // Добавляю данные для Cook
        return args -> {
            log.info("Preloading " + cookRepository.save(new Cook(/*"Зураб", "Белый", 5.0f, true*/)));
            log.info("Preloading " + cookRepository.save(new Cook(/*"Максим", "Иванов", 2.7f, false*/)));
        };
    }

    @Bean
    CommandLineRunner Dish(DishRepository dishRepository) { // Добавляю данные для Dish
        return args -> {
            log.info("Preloading " + dishRepository.save(new Dish()));
            log.info("Preloading " + dishRepository.save(new Dish()));
        };
    }

    @Bean
    CommandLineRunner Order(OrderRepository orderRepository) { // Добавляю данные для Order
        return args -> {
            log.info("Preloading " + orderRepository.save(new Order()));
            log.info("Preloading " + orderRepository.save(new Order()));
        };
    }
}
