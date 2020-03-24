package com.tinychiefdelights.database;

import com.tinychiefdelights.model.Admin;
import com.tinychiefdelights.model.Cook;
import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.Review;
import com.tinychiefdelights.repository.AdminRepository;
import com.tinychiefdelights.repository.CookRepository;
import com.tinychiefdelights.repository.CustomerRepository;
import com.tinychiefdelights.repository.ReviewRepository;
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
            log.info("Preloading " + customerRepository.save(new Customer("Артур", "Вартанян")));
            log.info("Preloading " + customerRepository.save(new Customer("Андрей", "Чекмарев")));
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
            log.info("Preloading " + cookRepository.save(new Cook("Зураб", "Белый", 5.0f, true)));
            log.info("Preloading " + cookRepository.save(new Cook("Максим", "Иванов", 2.7f, false)));
        };
    }
}
