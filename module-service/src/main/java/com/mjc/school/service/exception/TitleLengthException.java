package com.mjc.school.service.exception;

public class TitleLengthException extends CodedException{

    public TitleLengthException(String message) {
        super(message);
    }

    public String getCode() {
        return "000012";
    }
}
