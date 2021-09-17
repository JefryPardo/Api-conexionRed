package com.prueba.app_conexion.config.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResp {
    // Cambiar nombre ErrorResp
    
    private String response;
    private String message;
    private String status;

    public ErrorResp(String response, String message, String status) {
        this.response = response;
        this.message = message;
        this.status = status;
    }
}
