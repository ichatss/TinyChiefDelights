package com.tinychiefdelights.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@ApiModel
@Data
@Entity
@Table(name = "pg_user", schema = "public")
public class User implements UserDetails {

    // Roles
    //
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_COOK = "COOK";
    //


    public User() { // Пустой конструктор для Hibernate

    }


    // Поля
    private @Id
    @GeneratedValue
    Long id;

    @Column(name = "login")
    private String login;

    @Size(min = 5, max = 30)
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;


    // Методы
    //
    // GrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }


    // userName == login (одно и тоже)
    @Override
    public String getUsername() {
        return login;
    }


    // Во всех флагах стоит TRUE, так как они не используются
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
    //
}