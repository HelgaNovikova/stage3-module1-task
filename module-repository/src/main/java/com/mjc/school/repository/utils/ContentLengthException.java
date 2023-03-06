package com.mjc.school.repository.utils;

public class ContentLengthException extends CodedException{

    public ContentLengthException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "000012";
    }
}
