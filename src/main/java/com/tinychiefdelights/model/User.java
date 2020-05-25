package com.tinychiefdelights.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name = "pg_user", schema = "public")
public class User implements UserDetails {

    // Roles
    //
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_COOK = "COOK";
    public static final String ROLE_CHEF = "CHEF";
    //


    public User() { // Пустой конструктор для Hibernate

    }


    // Поля
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    @Size(min = 4, max = 24, message = "Логин должен быть не менее 4 символов и не более 24!")
    @Column(name = "login")
    private String login;

//    @Size(min = 6, max = 18, message = "Пароль должен быть не менее 6 символов и не более 18!")
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private String password2;

    @Column(name = "role")
    private String role;

//    @Size(min = 2, max = 26, message = "Имя должно быть не менее 2 символов и не более 26!")
    @Column(name = "name")
    private String name;

//    @Size(min = 2, max = 26, message = "Фамилия должна быть не менее 2 символов и не более 26!")
    @Column(name = "last_name")
    private String lastName;


    // Методы
    //
    // Возвращает авторизованного пользователя
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // GrantedAuthority
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }


    // userName == login (одно и тоже)
    @JsonIgnore
    @Override
    public String getUsername() {
        return login;
    }


    // Во всех флагах стоит TRUE, так как они не используются
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}