package com.prueba.app_conexion.service;

import java.util.List;
import com.prueba.app_conexion.dto.ConnectionHistoryDto;

public interface IConnectionHistoryService {
    
    List<ConnectionHistoryDto> searchConnectionHistoryByIdNetworkConnection(int idNetworkConnection);
}
