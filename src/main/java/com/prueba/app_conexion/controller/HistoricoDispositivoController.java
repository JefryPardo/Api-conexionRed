package com.prueba.app_conexion.controller;

import java.util.List;

import com.prueba.app_conexion.model.HistoricoDispositivo;
import com.prueba.app_conexion.service.IHistoricoDispositivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historicoDispositivo")
public class HistoricoDispositivoController {
    
    @Autowired
    private IHistoricoDispositivoService serviceHistoricoDispositivo;


	@GetMapping("/search/{mac}")
	public List<HistoricoDispositivo> buscar(@PathVariable("mac") String mac) {		
		return (List<HistoricoDispositivo>) serviceHistoricoDispositivo.buscarHistorialConexiones(mac);
	}


}
