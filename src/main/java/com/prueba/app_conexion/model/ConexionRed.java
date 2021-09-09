package com.prueba.app_conexion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "conexion_red" )
public class ConexionRed {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    
    private String tipo;
    
    @Column(name="nombre_red")
    private String nombreRed;
    
    @Column(name="tipo_cifrado")
    private String tipoCifrado;
    
    private String usuario;
    
    @Column(name="contrasenia_conexion")
    private String contraseniaConexion;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
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
    public String getContraseniaConexion() {
        return contraseniaConexion;
    }
    public void setContraseniaConexion(String contraseniaConexion) {
        this.contraseniaConexion = contraseniaConexion;
    }
    
   

    
}
