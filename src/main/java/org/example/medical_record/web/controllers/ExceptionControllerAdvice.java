package org.example.medical_record.web.controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.example.medical_record.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler({
            DiagnoseNotFoundException.class,
            DoctorNotFoundException.class,
            MedicalVisitNotFoundException.class,
            PatientNotFoundException.class,
            SpecialityNotFoundException.class,
            SickLeaveNotFoundException.class

    })
    public ResponseEntity<Void> handleNotFoundExceptions(Exception exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }




    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("type", "Validation");
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        responseBody.put("detail", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("type", "ConstraintViolation");
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        responseBody.put("detail", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }


}
