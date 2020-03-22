package com.tinychiefdelights.database;

import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.model.Review;
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
            log.info("Preloading " + customerRepository.save(new Customer("Artur", "Vartanyan")));
            log.info("Preloading " + customerRepository.save(new Customer("Andrey", "Chekmaryov")));
        };
    }

    @Bean
    CommandLineRunner Review(ReviewRepository reviewRepository) { // Добавляю данные для Review
        return args -> {
            log.info("Preloading " + reviewRepository.save(new Review("Выйграл лигу поваров 2015", 5.0f)));
            log.info("Preloading " + reviewRepository.save(new Review("Очень невкусно готовит", 2.4f)));
        };
    }
}
