package com.tinychiefdelights;

import com.tinychiefdelights.model.Customer;
import com.tinychiefdelights.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) { // Добавляю данные для Customer
        return args -> {
            log.info("Preloading " + repository.save(new Customer("Artur", "Vartanyan")));
            log.info("Preloading " + repository.save(new Customer("Andrey", "Chekmaryov")));
        };
    }
}
