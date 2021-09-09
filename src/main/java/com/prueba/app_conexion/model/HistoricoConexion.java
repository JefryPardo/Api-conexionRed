package com.prueba.app_conexion.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table( name = "historico_conexiones" )
public class HistoricoConexion {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private int tipo;
    
    @Column(name="nombre_red")
    private String nombreRed;
    
    @Column(name="tipo_cifrado")
    private String tipoCifrado;
    
    private String usuario;    
    private String mac;
    private String ip;
    
    @Column(name="tipo_dispositivo")
    private String tipoDispositivo;
    
    private String motivo;
    private int red;
    
    @Column(name="fecha_Creacion")
    private Date fechaCreacion;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public String getNombreRed() {
        return nombreRed;
    }
    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }
    public String getTipoCifrado() {
        return tipoCifrado;
    }
    public void setTipoCifrado(String tipoCifrado) {
        this.tipoCifrado = tipoCifrado;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getTipoDispositivo() {
        return tipoDispositivo;
    }
    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public int getRed() {
        return red;
    }
    public void setRed(int red) {
        this.red = red;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
      
     
}
