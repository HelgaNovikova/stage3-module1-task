package com.mjc.school.service.exception;

public abstract class CodedException extends RuntimeException {
    public CodedException(String message) {
        super(message);
    }

    public abstract String getCode();
}
