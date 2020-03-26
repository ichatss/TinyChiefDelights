package com.tinychiefdelights.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Невозможно найти id: " + id);
    }
}