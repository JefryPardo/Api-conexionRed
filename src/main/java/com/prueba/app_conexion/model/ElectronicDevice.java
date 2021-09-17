package com.prueba.app_conexion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "electronic_device" )
public class ElectronicDevice {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="id_device")
    private int idDevice;

    @NotBlank(message = "Mac: No validad.")
    @NotNull(message = "Mac: No validad.")
    private String mac;

    @NotBlank(message = "typeDevice: No valido.")
    @NotNull(message = "typeDevice: No valido.")
    @Column(name="type_device")
    private String typeDevice;  

    @NotNull(message = "connectionStatus: No valido.")
    @Column(name="connection_status")
    private boolean connectionStatus;

    @NotNull(message = "connection: No valido.")
    @ManyToOne
    @JoinColumn( name = "connection")
    private NetworkConnection connection;

    @NotBlank(message = "ip: No valido.")
    @NotNull(message = "ip: No valido.")
    private String ip;

    @NotBlank(message = "brand: No valido.")
    @NotNull(message = "brand: No valido.")
    private String brand;
}
