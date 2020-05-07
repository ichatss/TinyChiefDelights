package com.tinychiefdelights.configs;

import com.tinychiefdelights.model.Role;
import com.tinychiefdelights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Поля
    //
    private UserService userService;


    // Injects in SETTERS
    //
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    // Methods
    //
    // Тут мы переопределяем метод конфигураций
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole(String.valueOf(Role.ADMIN))
                .antMatchers("/cook/**").hasRole(String.valueOf(Role.COOK))
                .antMatchers("/customer/**").permitAll();
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }


    // Тут мы переопределяем для работы с внешней БД
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    // Тут мы используем encoder для шифрования паролей
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Возвращаем сервис пользовател для userDetServ
    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }
}