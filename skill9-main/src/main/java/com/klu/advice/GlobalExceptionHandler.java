package com.klu.advice;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.klu.dto.ErrorResponse;
import com.klu.exception.InvalidInputException;
import com.klu.exception.StudentNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex){

        ErrorResponse error=new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidInputException ex){

        ErrorResponse error=new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}