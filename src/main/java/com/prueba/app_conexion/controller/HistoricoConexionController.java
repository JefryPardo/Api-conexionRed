package com.prueba.app_conexion.controller;

import java.util.ArrayList;
import java.util.List;

import com.prueba.app_conexion.model.HistoricoConexion;
import com.prueba.app_conexion.service.IHistoricoConexionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historicoRed")
public class HistoricoConexionController {
    

    @Autowired
    private IHistoricoConexionService serviceHistoricoRed;

    
	@GetMapping("/search/{id}")
	public List<HistoricoConexion> buscar(@PathVariable("id") int idRed) {		
		return (List<HistoricoConexion>) serviceHistoricoRed.buscarHistorialDispositivoConEstaRed(idRed);
	}

}
