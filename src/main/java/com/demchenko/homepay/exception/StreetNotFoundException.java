package com.demchenko.homepay.exception;

public class StreetNotFoundException extends RuntimeException {
    public StreetNotFoundException(String message) {
        super(message);
    }
}
