package com.prueba.app_conexion.controller;

import java.util.List;

import javax.validation.Valid;

import com.prueba.app_conexion.dto.ElectronicDeviceDto;
import com.prueba.app_conexion.service.IElectronicDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class ElectronicDeviceController {

    @Autowired
    private IElectronicDeviceService serviceElectronicDevice;	

	/**
	 * Peticion que retorna todos los dispositivos electronicos
	 * @return lista tipo ElectronicDeviceDto
	 */
    @GetMapping("/all")
	public List<ElectronicDeviceDto> searchElectronicDeviceDtos() {

		return serviceElectronicDevice.searchAll();
	}

	/**
	 * Guarda el objecto tipo ElectronicDevice en la base de datos
	 * @param electronicDeviceDto
	 * @return json del objecto que se guardo.
	 */
	@PostMapping("/save")
	public ElectronicDeviceDto saveElectronicDeviceDto(@RequestBody @Valid ElectronicDeviceDto electronicDeviceDto) {

		return	this.serviceElectronicDevice.saveElectronicDevice(electronicDeviceDto);
	}

	/**
	 * Buscar mediante el String mac, parametro unico.
	 * @param mac
	 * @return json del objecto sí lo encontro, de lo contrario retorna null
	 */
	@GetMapping("/searchMac/{mac}")
	public ElectronicDeviceDto searchMacDto(@PathVariable("mac") String mac) {	

		return serviceElectronicDevice.searchMac(mac);
	}

	/**
	 * Busca mediante el id del dispositivo, parametro unico.
	 * @param idDispositivoElectronico
	 * @return json del objecto sí lo encontro, de lo contrario retorna null
	 */
	@GetMapping("/{id}")
	public ElectronicDeviceDto searchIdDto(@PathVariable("id") int idDispositivoElectronico) {

		return serviceElectronicDevice.searchById(idDispositivoElectronico);
	}
	
	/**
	 * Borra mediante el id de dispositivo electronico.
	 * @param idDispositivoElectronico
	 * @return 
	 */
	@DeleteMapping("/{id}")
	public boolean borrar(@PathVariable("id") int idDispositivoElectronico) {

		return serviceElectronicDevice.deleteByIdElectronicDevice(idDispositivoElectronico);
	}
}
