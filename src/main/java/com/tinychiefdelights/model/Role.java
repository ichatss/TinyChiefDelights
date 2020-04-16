package com.tinychiefdelights.model;

 // ОН ПОКА ПРОСТО СУЩЕСТВУЕТ, В ПРОЕКТ ЭТОТ ENUM НИКАК НЕ ИНТЕГРИРОВАН

public enum Role {

    COOK("cook"), ADMIN("admin"), CUSTOMER("customer");

    // Fields
    private String role;


    // Constructor
    Role(String role){
        this.role = role;
    }
}