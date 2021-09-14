package com.prueba.app_conexion.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "device_history" )
public class DeviceHistory {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="id_device_history")
    private int idDeveiceHistory;
    private String mac;

    @Column(name="type_device")
    private String typeDevice;   
    
    private String ip;    

    @Column(name="id_network")   
    private int idNetwork;

    @Column(name="network_name")
    private String networkName;

    @Column(name="type_standard")
    private String typeStandard; 

    @Column(name="user_nick")   
    private String userNick;

    @Column(name="creation_date")
    private Date creationDate;
    private String reason;        
}
