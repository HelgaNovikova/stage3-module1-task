package com.mjc.school.repository.utils;

public abstract class CodedException extends RuntimeException {
    public CodedException(String message) {
        super(message);
    }

    public abstract String getCode();
}
