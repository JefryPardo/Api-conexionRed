package com.prueba.app_conexion.controller;

import java.util.List;
import com.prueba.app_conexion.dto.DeviceHistoryDto;
import com.prueba.app_conexion.service.IDeviceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deviceHistories")
public class DeviceHistoryController {
    
    @Autowired
    private IDeviceHistoryService serviceDeviceHistory;

	@GetMapping("/{mac}")
	public List<DeviceHistoryDto> buscar(@PathVariable("mac") String mac) {	

		return serviceDeviceHistory.searchDeviceHistoryByMac(mac);
	}
}