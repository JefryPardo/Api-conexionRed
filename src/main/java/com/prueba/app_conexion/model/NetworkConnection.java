package com.prueba.app_conexion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "network_connection" )
public class NetworkConnection {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="id_connection")
    private int idConnection;

    @Min(1)
    @Max(2)
    @NotNull(message = "typeConnection: No puede estar vacio.")
    @Column(name="type_connection")
    private int typeConnection;

    @NotBlank(message = "networkName: Nombre de la red no valida.")
    @Column(name="network_name")
    private String networkName;    

    @NotNull(message = "typeStandard: Estandar no valido.")
    @Column(name="type_standard")
    private String typeStandard;    

    @NotNull(message = "userNick: Se requiere un usuario valido.")
    @NotBlank(message = "userNick: El usuario no puede estar vacio.")
    @Column(name="user_nick")
    private String userNick;
    
    @NotBlank(message = "userPassword: Contraseña mo validad.")
    @Column(name="user_password")
    private String userPassword;    
}
