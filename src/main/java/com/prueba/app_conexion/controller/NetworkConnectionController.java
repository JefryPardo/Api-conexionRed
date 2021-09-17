package com.prueba.app_conexion.controller;

import java.util.List;
import javax.validation.Valid;
import com.prueba.app_conexion.config.exception.ErrorResp;
import com.prueba.app_conexion.dto.NetworkConnectionDto;
import com.prueba.app_conexion.service.INetworkConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/networks")
public class NetworkConnectionController {
    
    @Autowired
    private INetworkConnectionService serviceNetwordConnection;

	/**
	 * Busca todas las conexiones de la network.
	 * @author Jefry Pardo.
	 * @return lista de todas las conexiones en formato Json si encuntra, de lo contrario se retorna una lista vacia 
	 */
    @GetMapping
	public List<NetworkConnectionDto> searchNetworkConnections() {
		
		return serviceNetwordConnection.searchAll();
	}
	
	/**
	 * Guarda la network tipo NetworkConnection
	 * @param network
	 * @return la network guardada en formato json y si encuentra errores se retorna un mensaje 
	 */
	@PostMapping
	public NetworkConnectionDto save(@Valid @RequestBody NetworkConnectionDto network) {

		return serviceNetwordConnection.saveNetworkConnection(network);
	}

	/**
	 * Busca la network mediante el id
	 * @param idConexionRed
	 * @return la network en formato Json si la encuentra, de lo contrario retorna null
	 */
	@GetMapping("/{id}")
	public NetworkConnectionDto searchByIdNetworkConnection(
		@PathVariable("id") int idNetworkConnection) {		
		
		return serviceNetwordConnection.searchById(idNetworkConnection);
	}
	
	/**
	 * 
	 * @param idConexionRed
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ErrorResp deleteById(@PathVariable("id") int idNetworkConnection) {

		return serviceNetwordConnection.deleteByIdNetworkConnection(idNetworkConnection);
	}

	/**
	 * busca todas las conexiones disponibles
	 * @return una lista tipo NetworkConnection, de lo contrario retorna una lista vacia
	 */
	@GetMapping("/available")
	public List<NetworkConnectionDto> avaibleNetworkConnectionDtos() {

		return serviceNetwordConnection.availableNetworks();
	}
}