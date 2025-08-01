package com.practice.demo_project.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserException extends RuntimeException{
    private HttpStatus statusCode;

    public UserException(String message, HttpStatus statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
