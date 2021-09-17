package com.prueba.app_conexion.config;

import com.prueba.app_conexion.config.exception.BadRequestException;
import com.prueba.app_conexion.config.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notDoundException(Exception e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(Exception e) {
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}