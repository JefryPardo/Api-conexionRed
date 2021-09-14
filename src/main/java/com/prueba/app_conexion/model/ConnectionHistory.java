package com.prueba.app_conexion.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "connection_history" )
public class ConnectionHistory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="id_connection_history")
    private int idConnectionHistory;
    @Column(name="type_connection")
    private int typeConnection;
    
    @Column(name="network_name")
    private String networkName;
    
    @Column(name="type_standard")
    private String typeStandard;    
    private String user_nick;    
    private String mac;
    private String ip;
    
    @Column(name="type_device")
    private String typeDevice;

    private String reason;
    private int network;
    
    @Column(name="creation_date")
    private Date creationDate;  
}
