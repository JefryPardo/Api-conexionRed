package com.prueba.app_conexion.service;

import java.util.ArrayList;
import com.prueba.app_conexion.model.HistoricoDispositivo;

public interface IHistoricoDispositivoService {
    
    ArrayList<HistoricoDispositivo> buscarHistorialConexiones(String mac_id);
    
}
