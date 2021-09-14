package com.prueba.app_conexion.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionHistoryDto {
    
    private int idConnectionHistory;
    private int typeConnection;
    private String networkName;
    private String typeStandard;    
    private String user_nick;    
    private String mac;
    private String ip;
    private String typeDevice;
    private String reason;
    private int network;
    private Date creationDate;
}
