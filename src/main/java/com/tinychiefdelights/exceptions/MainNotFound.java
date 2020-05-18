package com.tinychiefdelights.exceptions;

public class MainNotFound extends RuntimeException {

    public MainNotFound(Long id) {
        super("Невозможно найти id: " + id);
    }
}