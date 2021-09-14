package com.prueba.app_conexion.config.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {    
    }

    public NotFoundException(String message) {
        super(message);
    }
}
