package com.mjc.school.service.exception;

public class ContentLengthException extends CodedException{

    public ContentLengthException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "000012";
    }
}
