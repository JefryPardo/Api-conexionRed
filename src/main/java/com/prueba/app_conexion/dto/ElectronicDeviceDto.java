package com.prueba.app_conexion.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectronicDeviceDto {

    private int idDevice;
    
    @NotBlank(message = "Mac: No validad.")
    @NotNull(message = "Mac: No validad.")
    private String mac;

    @NotBlank(message = "typeDevice: No valido.")
    @NotNull(message = "typeDevice: No valido.")
    private String typeDevice;  

    @NotNull(message = "connectionStatus: No valido.")
    private boolean connectionStatus;

    @NotNull(message = "connection: No valido.")
    private NetworkConnectionDto connection;

    @NotBlank(message = "ip: No valido.")
    @NotNull(message = "ip: No valido.")
    private String ip;

    @NotBlank(message = "brand: No valido.")
    @NotNull(message = "brand: No valido.")
    private String brand;
}
