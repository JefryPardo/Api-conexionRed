package com.prueba.app_conexion.config.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResp {
    
    private String Error;
    private String message;
    private String state;

    public ErrorResp(String Error, String message, String state) {
        this.Error = Error;
        this.message = message;
        this.state = state;
    }
}
