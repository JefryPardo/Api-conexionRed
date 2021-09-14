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

    @NotBlank(message = "Se requiere una mac validad.")
    @NotNull(message = "mac no puede ser null.")
    private String mac;

    @NotBlank
    @NotNull
    @Column(name="type_device")
    private String typeDevice;  

    @NotNull
    @Column(name="connection_status")
    private boolean connectionStatus;

    @NotNull
    @ManyToOne
    @JoinColumn( name = "connection")
    private NetworkConnection connection;

    @NotBlank
    @NotNull
    private String ip;

    @NotBlank
    @NotNull
    private String brand;
}
