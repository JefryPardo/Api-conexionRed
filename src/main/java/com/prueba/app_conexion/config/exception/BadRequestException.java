package com.prueba.app_conexion.config.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {
    }
    
    public BadRequestException(String message) {
        super(message);
    }    
}
