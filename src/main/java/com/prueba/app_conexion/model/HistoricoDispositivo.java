package com.prueba.app_conexion.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "historico_dispositivo" )
public class HistoricoDispositivo {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    private String mac;
    private String tipo;
    
    @Column(name="fecha_creacion")
    private Date fechaCreacion;
    private String ip;

    @Column(name="nombre_red")
    private String nombreRed;
    
    @Column(name="tipo_cifrado")
    private String tipoCifrado;
    
    private String usuario;
    private String motivo;


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
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
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
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }    
}
