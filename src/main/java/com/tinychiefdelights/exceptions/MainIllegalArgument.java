package com.tinychiefdelights.exceptions;

public class MainIllegalArgument extends RuntimeException {

    public MainIllegalArgument() {
        super("Ошибка! Проверьте заполненные поля на корректность!");
    }

    public MainIllegalArgument(String s) {
        super("Ошибка! " + s);
    }
}
