package com.prueba.app_conexion.controller;

import java.util.List;
import java.util.Optional;
import com.prueba.app_conexion.model.ConexionRed;
import com.prueba.app_conexion.service.IConexionRedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/red")
public class ConexionRedController {
    
    @Autowired
    private IConexionRedService serviceConexionRed;

    @GetMapping("/all")
	public List<ConexionRed> conexionesRed() {
		return (List<ConexionRed>) serviceConexionRed.buscarTodas();
	}
	
	@PostMapping("/save")
	public ConexionRed guardar(@RequestBody ConexionRed red) {
		return	this.serviceConexionRed.guardar(red);
	}

	@GetMapping("/search/{id}")
	public Optional<ConexionRed> buscar(@PathVariable("id") int idConexionRed) {		
		return serviceConexionRed.buscarPorId(idConexionRed);
	}

	@GetMapping("/delete/{id}")
	public boolean borrar(@PathVariable("id") int idConexionRed) {		
		return serviceConexionRed.eliminar(idConexionRed);
	}

	@GetMapping("/available")
	public List<ConexionRed> redesDisponibles() {
		return (List<ConexionRed>) serviceConexionRed.buscarDisponibles();
	}

}
