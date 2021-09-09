package com.prueba.app_conexion.service;

import java.util.List;
import java.util.Optional;

import com.prueba.app_conexion.model.ConexionRed;

public interface IConexionRedService {
    
    List<ConexionRed> buscarTodas();
    Optional<ConexionRed> buscarPorId(int idConexionRed);
    ConexionRed guardar(ConexionRed conexionRed);
    boolean eliminar(Integer id);
    List<ConexionRed> buscarDisponibles();
    
}
