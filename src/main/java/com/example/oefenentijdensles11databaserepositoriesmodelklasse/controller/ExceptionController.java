package com.example.oefenentijdensles11databaserepositoriesmodelklasse.controller;

import com.example.oefenentijdensles11databaserepositoriesmodelklasse.exception.NameDuplicateException;
import com.example.oefenentijdensles11databaserepositoriesmodelklasse.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameDuplicateException.class)
    public ResponseEntity<String> handleNameDuplicate(NameDuplicateException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
