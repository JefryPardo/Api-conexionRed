package com.prueba.app_conexion.controller;

import java.util.List;
import com.prueba.app_conexion.dto.ConnectionHistoryDto;
import com.prueba.app_conexion.service.IConnectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connectionHistories")
public class ConnectionHistoryController {    

    @Autowired
    private IConnectionHistoryService serviceConnectionHistory;
    
	@GetMapping("/{id}")
	public List<ConnectionHistoryDto> buscar(@PathVariable("id") int idNetworkConnection) {	

		return serviceConnectionHistory.searchConnectionHistoryByIdNetworkConnection(idNetworkConnection);
	}
}