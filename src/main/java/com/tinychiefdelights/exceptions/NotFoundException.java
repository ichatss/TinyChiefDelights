package com.tinychiefdelights.exceptions;

// ОДИН ОБЩИЙ ДЛЯ ВСЕХ КАК И ДОГОВАРИВАЛИСЬ

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Couldn't find " + id);
    }
}