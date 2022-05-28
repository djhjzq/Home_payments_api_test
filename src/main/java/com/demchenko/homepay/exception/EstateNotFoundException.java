package com.demchenko.homepay.exception;

public class EstateNotFoundException extends RuntimeException {
    public EstateNotFoundException(String message) {
        super(message);
    }
}
