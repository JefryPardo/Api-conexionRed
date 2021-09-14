package com.prueba.app_conexion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectronicDeviceDto {

    private int idDevice;
    private String mac;
    private String typeDevice;
    private boolean connectionStatus;
    private NetworkConnectionDto connection;
    private String ip;
    private String brand;
}
