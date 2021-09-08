package com.prueba.app_conexion.controller;


import java.util.ArrayList;
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
	public ArrayList<ConexionRed> conexionesRed() {
		return (ArrayList<ConexionRed>) serviceConexionRed.buscarTodas();
	}
	

	@PostMapping("/save")
	public ConexionRed guardar(@RequestBody ConexionRed red){
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
	public ArrayList<ConexionRed> redesDisponibles() {
		return (ArrayList<ConexionRed>) serviceConexionRed.buscarDisponibles();
	}

	









	// @GetMapping("/delete/{id}")
	// public String eliminar(@PathVariable("id") int idRed, RedirectAttributes attributes) {

	// 	List<DispositivoElectronico> lista = serviceDispositivoElectronico.buscarPorConexionRed(idRed);
	// 	if(lista.size() > 0){
	// 		for (int j=0;j<lista.size();j++) {  
	// 			serviceDispositivoElectronico.eliminar(lista.get(j).getId());
	// 		}
	// 		serviceConexionRed.eliminar(idRed);
	// 	}else{
	// 		serviceConexionRed.eliminar(idRed);		
	// 	}

	// 	attributes.addFlashAttribute("msg", "La conexion y sus dispositivos fueron eliminados.");
	// 	return "redirect:/conexionRedController/vista";
		
	// }



	// @GetMapping("/create")
	// public String crearDispositivo(@ModelAttribute ConexionRed red,Model modelo,RedirectAttributes attributes) {	
		
	// 	modelo.addAttribute("red", red);	
	// 	return "conexionRed/formConexionRed";

	// }
	


}
