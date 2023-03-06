package com.mjc.school.service.exception;

import com.mjc.school.repository.utils.CodedException;

public class AuthorNotFoundException extends CodedException {
    public AuthorNotFoundException(String message) {
        super(message);
    }

    public String getCode() {
        return "000002";
    }
}
