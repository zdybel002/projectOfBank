package com.zdybel.course.Kantor.Errors;

public class DuplicateCurrencyException extends RuntimeException {
    public DuplicateCurrencyException(String message) {
        super(message);
    }
}
