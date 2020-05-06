package com.tinychiefdelights.exceptions;

public class SecurityException extends RuntimeException {

    public SecurityException(){
        super("Внимание! Вы не авторизованы в системе!");
    }
}
