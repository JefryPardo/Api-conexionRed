package com.prueba.app_conexion.service;

import java.util.ArrayList;
import java.util.Optional;

import com.prueba.app_conexion.model.ConexionRed;

public interface IConexionRedService {
    
    ArrayList<ConexionRed> buscarTodas();
    Optional<ConexionRed> buscarPorId(int idConexionRed);
    ConexionRed guardar( ConexionRed conexion_red);
    boolean eliminar(Integer id);
    ArrayList<ConexionRed> buscarDisponibles();
    
}
