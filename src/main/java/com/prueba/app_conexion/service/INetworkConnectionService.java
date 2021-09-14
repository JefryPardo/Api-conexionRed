package com.prueba.app_conexion.service;

import java.util.List;
import javax.validation.Valid;

import com.prueba.app_conexion.config.exception.ErrorResp;
import com.prueba.app_conexion.dto.NetworkConnectionDto;

public interface INetworkConnectionService {
    
    List<NetworkConnectionDto> searchAll();
    NetworkConnectionDto searchById(int idConnectionRed);
    NetworkConnectionDto saveNetworkConnection(@Valid NetworkConnectionDto networkDto);
    ErrorResp deleteByIdNetworkConnection(int idConnectionRed);
    List<NetworkConnectionDto> availableNetworks();      
    NetworkConnectionDto save(@Valid NetworkConnectionDto networkDto);  
}
