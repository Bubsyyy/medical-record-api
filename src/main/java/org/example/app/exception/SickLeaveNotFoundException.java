package org.example.app.exception;

public class SickLeaveNotFoundException extends RuntimeException {
    public SickLeaveNotFoundException(String message) {
        super(message);
    }
}
