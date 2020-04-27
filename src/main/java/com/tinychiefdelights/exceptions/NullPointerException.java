package com.tinychiefdelights.exceptions;

public class NullPointerException extends RuntimeException {

    public NullPointerException(){
        super("Ошибка! Проверьте заполненные поля на корректность!");
    }
}
