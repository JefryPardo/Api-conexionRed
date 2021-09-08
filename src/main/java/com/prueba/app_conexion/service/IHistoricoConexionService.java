package com.prueba.app_conexion.service;

import java.util.ArrayList;

import com.prueba.app_conexion.model.HistoricoConexion;

public interface IHistoricoConexionService {
    
    ArrayList<HistoricoConexion> buscarHistorialDispositivoConEstaRed(int redId);

}
