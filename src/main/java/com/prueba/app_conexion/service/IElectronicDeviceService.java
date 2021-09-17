package com.prueba.app_conexion.service;

import java.util.List;
import javax.validation.Valid;
import com.prueba.app_conexion.config.exception.ErrorResp;
import com.prueba.app_conexion.dto.ElectronicDeviceDto;

public interface IElectronicDeviceService {

    ElectronicDeviceDto save(@Valid ElectronicDeviceDto electronicDeviceDto);
    List<ElectronicDeviceDto> searchAll();
    ElectronicDeviceDto searchById(int idDispositivoElectronico);
    ElectronicDeviceDto saveElectronicDevice(@Valid ElectronicDeviceDto electronicDeviceDto);
    ElectronicDeviceDto searchMac(String mac);
    List<ElectronicDeviceDto> searchByConnection(int idConnection);
    ErrorResp deleteByIdElectronicDevice(Integer idElectronicDevice);   
}
