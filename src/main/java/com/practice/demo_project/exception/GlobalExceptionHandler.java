package com.practice.demo_project.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<Map<String, String>> handleUserException(UserException ex){
        Map<String, String> errMap = new HashMap<>();
        errMap.put("message", ex.getMessage());
        errMap.put("statusCode", ex.getStatusCode().toString());

        return ResponseEntity.status(ex.getStatusCode()).body(errMap);
    }
}
