package org.example.app.exception;

public class MedicalVisitNotFoundException extends RuntimeException {
    public MedicalVisitNotFoundException(String message) {
        super(message);
    }
}
