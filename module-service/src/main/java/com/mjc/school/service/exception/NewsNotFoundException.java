package com.mjc.school.service.exception;

public class NewsNotFoundException extends CodedException{
    public NewsNotFoundException(String message) {
        super(message);
    }

    public String getCode() {
        return "000001";
    }
}
