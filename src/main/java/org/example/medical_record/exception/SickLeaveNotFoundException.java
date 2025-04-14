package org.example.medical_record.exception;

public class SickLeaveNotFoundException extends RuntimeException {
    public SickLeaveNotFoundException(String message) {
        super(message);
    }
}
