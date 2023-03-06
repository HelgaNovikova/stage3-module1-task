package com.mjc.school.repository.utils;

public class AuthorNameException extends CodedException {

    public AuthorNameException(String message) {
        super(message);
    }

    public String getCode() {
        return "000012";
    }
}
