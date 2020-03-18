package com.tinychiefdelights.exceptions;

// ПОТОМ ВМЕСТЕ СДЕЛАЕМ ОДИН ОБЩИЙ ДЛЯ ВСЕХ КАК И ДОГОВАРИВАЛИСЬ

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Couldn't find customer " + id);
    }
}