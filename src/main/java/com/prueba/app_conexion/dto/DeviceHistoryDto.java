package com.prueba.app_conexion.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceHistoryDto {
    
    private int idDeveiceHistory;
    private String mac;
    private String typeDevice;
    private Date creationDate;
    private String ip;
    private String networkName;
    private String typeStandard;   
    private String userNick;
    private String reason; 
}
