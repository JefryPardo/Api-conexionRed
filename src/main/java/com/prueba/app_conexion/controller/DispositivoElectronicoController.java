package com.prueba.app_conexion.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.prueba.app_conexion.model.DispositivoElectronico;
import com.prueba.app_conexion.service.IDispositivoElectronicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DispositivoElectronicoController {

    @Autowired
    private IDispositivoElectronicoService serviceDispositivoElectronico;

	


    @GetMapping("/all")
	public ArrayList<DispositivoElectronico> dispositivos() {
		return (ArrayList<DispositivoElectronico>) serviceDispositivoElectronico.buscarTodas();
	}

	
	@PostMapping("/save")
	public DispositivoElectronico guardar(@RequestBody DispositivoElectronico dispositivo){
		return	this.serviceDispositivoElectronico.guardar(dispositivo);
	}


	@GetMapping("/searchMac/{mac}")
	public Optional<DispositivoElectronico> buscarMac(@PathVariable("mac") String mac) {		
		return serviceDispositivoElectronico.buscarMac(mac);
	}


	@GetMapping("/search/{id}")
	public Optional<DispositivoElectronico> buscarId(@PathVariable("id") int id) {		
		return serviceDispositivoElectronico.buscarPorId(id);
	}


	// Esta red tipo wifi tiene un smartphone?
	@GetMapping("/stock/smartphone/wifi")
	public boolean validarSmartphoneRedTipoWifi(@RequestBody DispositivoElectronico dispositivo) {		
		return serviceDispositivoElectronico.validarSmartphoneWifi(dispositivo);
	}


	@GetMapping("/delete/{id}")
	public boolean borrar(@PathVariable("id") int idDispositivoElectronico) {		
		return serviceDispositivoElectronico.eliminar(idDispositivoElectronico);
	}

}
