package com.prueba.app_conexion.service;

import java.util.List;
import com.prueba.app_conexion.dto.ElectronicDeviceDto;

public interface IElectronicDeviceService {

    ElectronicDeviceDto save(ElectronicDeviceDto electronicDeviceDto);
    List<ElectronicDeviceDto> searchAll();
    ElectronicDeviceDto searchById(int idDispositivoElectronico);
    ElectronicDeviceDto saveElectronicDevice( ElectronicDeviceDto electronicDeviceDto);
    ElectronicDeviceDto searchMac(String mac);
    List<ElectronicDeviceDto> searchByConnection(int idConnection);
    boolean deleteByIdElectronicDevice(Integer idElectronicDevice);   
}
