package org.example.medical_record.exception;

public class DiagnoseNotFoundException extends RuntimeException {
    public DiagnoseNotFoundException(String message) {
        super(message);
    }
}
