package com.mjc.school.service.exception;

public class AuthorNameException extends CodedException {

    public AuthorNameException(String message) {
        super(message);
    }

    public String getCode() {
        return "000012";
    }
}
