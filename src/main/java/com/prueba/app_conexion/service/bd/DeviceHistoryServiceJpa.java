package com.prueba.app_conexion.service.bd;

import java.util.List;
import java.util.stream.Collectors;

import com.prueba.app_conexion.dto.DeviceHistoryDto;
import com.prueba.app_conexion.model.DeviceHistory;
import com.prueba.app_conexion.repository.DeviceHistoryRepository;
import com.prueba.app_conexion.service.IDeviceHistoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DeviceHistoryServiceJpa implements IDeviceHistoryService {

    @Autowired
    private DeviceHistoryRepository deviceHistoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DeviceHistoryDto> searchDeviceHistoryByMac(String mac) {        
        List<DeviceHistory> listDevice = deviceHistoryRepo.findByMac(mac);
        return listDevice.stream()
        .map(DeviceHistory -> modelMapper.map(DeviceHistory, DeviceHistoryDto.class))
        .collect(Collectors.toList());
    }    
}
