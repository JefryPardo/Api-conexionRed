package com.prueba.app_conexion.service;

import java.util.List;
import com.prueba.app_conexion.dto.DeviceHistoryDto;

public interface IDeviceHistoryService {
    
    List<DeviceHistoryDto> searchDeviceHistoryByMac(String mac);    
}
