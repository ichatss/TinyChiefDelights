package com.tinychiefdelights.exceptions;

public class MainNullPointer extends RuntimeException {

    public MainNullPointer() {
        super("Ошибка! Проверьте заполненные поля на корректность!");
    }

    public MainNullPointer(String s) {
        super("Ошибка! " + s);
    }
}
