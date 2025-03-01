package com.capgemini.employeepayrollapplication.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class EmployeePayrollExceptionHandler extends RuntimeException {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
    Map<String, String> response = new HashMap<>();

    exception.getBindingResult().getFieldErrors().forEach(error -> {
        response.put(error.getField(),error.getDefaultMessage());
    });
    return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);

  }
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Map<String, String>> handleNoSuchElementException(NoSuchElementException exception) {
    Map<String, String> response = new HashMap<>();

    response.put("Employee not found in the database", exception.getMessage());
    return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
  }
}