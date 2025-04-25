package org.example.app.web.controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.example.app.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
