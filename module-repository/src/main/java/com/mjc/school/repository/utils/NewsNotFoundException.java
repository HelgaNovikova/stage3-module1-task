package com.mjc.school.repository.utils;

public class NewsNotFoundException extends CodedException{
    public NewsNotFoundException(String message) {
        super(message);
    }

    public String getCode() {
        return "000001";
    }
}
