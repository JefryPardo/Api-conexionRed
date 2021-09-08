package com.prueba.app_conexion.controller;

import java.util.ArrayList;
import java.util.List;

import com.prueba.app_conexion.model.HistoricoDispositivo;
import com.prueba.app_conexion.service.IHistoricoDispositivoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historicoDispositivo")
public class HistoricoDispositivoController {
    
    @Autowired
    private IHistoricoDispositivoService serviceHistoricoDispositivo;


	@GetMapping("/search/{mac_id}")
	public ArrayList<HistoricoDispositivo> buscar(@PathVariable("mac_id") String mac_id) {		
		return (ArrayList<HistoricoDispositivo>) serviceHistoricoDispositivo.buscarHistorialConexiones(mac_id);
	}


}
