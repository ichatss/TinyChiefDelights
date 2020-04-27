package com.tinychiefdelights.exceptions;

public class IllegalArgumentException extends RuntimeException{

    public IllegalArgumentException(){
        super("Ошибка! Проверьте заполненные поля на корректность!");
    }
}
