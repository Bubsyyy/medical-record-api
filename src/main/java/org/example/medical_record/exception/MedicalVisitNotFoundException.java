package org.example.medical_record.exception;

public class MedicalVisitNotFoundException extends RuntimeException {
    public MedicalVisitNotFoundException(String message) {
        super(message);
    }
}
