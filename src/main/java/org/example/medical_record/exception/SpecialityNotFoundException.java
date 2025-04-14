package org.example.medical_record.exception;

public class SpecialityNotFoundException extends RuntimeException {
    public SpecialityNotFoundException(String message) {
        super(message);
    }
}
