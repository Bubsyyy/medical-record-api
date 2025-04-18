package org.example.app.exception;

public class SpecialityNotFoundException extends RuntimeException {
    public SpecialityNotFoundException(String message) {
        super(message);
    }
}
