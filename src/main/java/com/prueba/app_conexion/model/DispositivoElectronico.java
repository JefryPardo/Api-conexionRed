package com.prueba.app_conexion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "dispositivo_electronico" )
public class DispositivoElectronico {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id; 


    private String mac;
    private String tipo;
    
    @Column(name="estado_conexion")
    private boolean estadoConexion;

    @ManyToOne
    @JoinColumn( name = "conexion")
    private ConexionRed conexion;

    private String ip;
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ConexionRed getConexion() {
        return conexion;
    }

    public void setConexion(ConexionRed conexion) {
        this.conexion = conexion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isEstadoConexion() {
        return estadoConexion;
    }

    public void setEstadoConexion(boolean estadoConexion) {
        this.estadoConexion = estadoConexion;
    }     
    
}
