package com.prueba.app_conexion.service.bd;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.prueba.app_conexion.config.exception.BadRequestException;
import com.prueba.app_conexion.config.exception.ErrorResp;
import com.prueba.app_conexion.config.exception.NotFoundException;
import com.prueba.app_conexion.dto.ElectronicDeviceDto;
import com.prueba.app_conexion.dto.NetworkConnectionDto;
import com.prueba.app_conexion.model.ElectronicDevice;
import com.prueba.app_conexion.model.NetworkConnection;
import com.prueba.app_conexion.repository.ElectronicDeviceRepository;
import com.prueba.app_conexion.repository.NetworkConnectionRepository;
import com.prueba.app_conexion.service.INetworkConnectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

@Service
@Primary
@Validated
public class NetworkConnectionServiceJpa implements INetworkConnectionService{

    @Autowired
    private NetworkConnectionRepository networkConnectioRepo;

    @Autowired
    @Lazy
    private ElectronicDeviceRepository dispositivoElectronicoRepo;

    @Autowired
    private ModelMapper modelMapper;
    
    /**
	 * busca todo los network tipo NetworkConnection
	 * @return lista tipo NetworkConnectionDto, puede estar vacia.
	 */
    @Override
    public List<NetworkConnectionDto> searchAll() {

        List<NetworkConnection> listNetwork = networkConnectioRepo.findAll();
        if(listNetwork.size() > 0) {

            return listNetwork.stream()
            .map(NetworkConnection -> modelMapper.map(NetworkConnection, NetworkConnectionDto.class))
            .collect(Collectors.toList());
        }else {
           
            throw new NotFoundException("No se encontro ninguna red.");
        }        
    }   

    /**
	 * Busca mediante el idnetwork
	 * @param idConexionRed
	 * @return el objecto en formato json si lo encuentra, de lo contrario retorna null
	 */
    @Override
    public NetworkConnectionDto searchById(int idConexionRed) {

        Optional<NetworkConnection> network = networkConnectioRepo.findById(idConexionRed);
        if(network.isPresent()) {

            return modelMapper.map(network.get(), NetworkConnectionDto.class);
        }else{

            throw new NotFoundException("No se encontro ninguna red con el id: "+idConexionRed);
        }            
    }    

    /**
     * Se encarga de borrar una coenxión de la base de datos, por id 
     * @param id Corresponde al id de la conexión a eliminar
     * @return retorna false en caso de que no sea posible borrar el registro. true en caso contrario.
     */
    @Override
    public ErrorResp deleteByIdNetworkConnection(int idConnection) {
        
        if(isEmpty(idConnection)) {
            throw new NotFoundException("El network tiene dispositivos electronicos enlazados, primero elimine sus relaciones.");
        }else {

            return responseDeleteByIdNetworkConnection(idConnection);
        }         
    }

    /**
     * Valida si idConnection tiene relacion con algun dispositivo electronico
     * @param idConnection
     * @return true si tiene alguna relacion, de lo contrario false
     */
    public boolean isEmpty(int idConnection) {

        List<ElectronicDeviceDto> allDevices = searchByIdConnection(idConnection);
        return (allDevices.size() > 0);
    }

    /**
     * Metodo usado en deleteByIdNetworkConnection() y Borra la network segun el id del parametro sí existe, de lo contrario genera una exception 
     * @param idConnection entero
     * @return una respuesta tipo ErrorResp
     */
    public ErrorResp responseDeleteByIdNetworkConnection(int idConnection) {
        try {
            networkConnectioRepo.deleteById(idConnection);
             return new ErrorResp("Con exito.","Se pudo borrar la network con id: "+idConnection,"200 OK");
        } catch (Exception e) {
            return new ErrorResp("Sin exito","No se pudo borrarla network con id: "+idConnection,"404 Not Found");                
        }
    }

    /**
	 * busca las networks disponibles
	 * @return lista tipo NetworkConnectionDto, puede estar vacia
    */
    @Override
    public List<NetworkConnectionDto> availableNetworks() {
        
        List<ElectronicDeviceDto> allDevices = searchAllDevices();
		List<NetworkConnectionDto> allNetworks = searchAllNetworks();        
        return iteratedList(allDevices, allNetworks);        
    } 
    
    /**
     * Itera dos listas y saca aquellas networs que tengan menos de tres dispositivos conectados
     * @param allDevices list tipo ElectronicDeviceDto
     * @param allNetworkslist tipo NetworkConnectionDto
     * @return lista tipo NetworkConnectionDto con las networks con menos de 3 dispositivos electronicos
     */
    public List<NetworkConnectionDto> iteratedList(List<ElectronicDeviceDto> allDevices, List<NetworkConnectionDto> allNetworks) {
        List<NetworkConnectionDto> listNetworks = new ArrayList<>();
        for (NetworkConnectionDto network : allNetworks) 
        {
            int count = 0; 
            for (ElectronicDeviceDto device : allDevices) 
            { 
                if(network.getIdConnection() == device.getConnection().getIdConnection()) {
                    count+=1;
                } 
            }
            if(count < 3) {
                listNetworks.add(network);
            }
        }
        return listNetworks;
    }

    /**
	 * guarda la network y captura errores de validaciones si los hay.
	 * @param networkDto
	 * @return el objecto tipo NetworkConnectionDto si se guarda con exito, de lo contrario retorna el error presentado
	 */
    @Override
    public NetworkConnectionDto saveNetworkConnection(NetworkConnectionDto networkDto) {

        if(networkDto.getTypeConnection() == 1) {            
            if(!containes(networkDto)) {

                throw new BadRequestException("Invalid standard for connection type. Expected: WEP, WPA, WPA2");
            }
            return save(networkDto);
        }else {

            return save(networkDto);
        }
    }

    /**
     * Metodo usado en saveNetworkConnection()
     * @param networkDto tipo NetworkConnectionDto
     * @return true si networkDto.getTypeStandard esta en la lista Standards permitidos
     */
    public boolean containes(NetworkConnectionDto networkDto) {
        String[] Standards = {"WEP","WPA","WPA2"};
        return Arrays.asList(Standards).contains(networkDto.getTypeStandard());
    }

    /**
	 * guarda y hace uso de try-catch para capturar errores si es necesario
	 * @param networkDto
	 * @return el objecto tipo NetworkConnectionDto si se guarda con exito de lo contrario retorna el error presentado
	 */
    public NetworkConnectionDto save(NetworkConnectionDto networkDto) {        

        NetworkConnection network = modelMapper.map(networkDto, NetworkConnection.class);
        network = networkConnectioRepo.save(network);
        
        return modelMapper.map(network,NetworkConnectionDto.class);        
    }

    /**
     * metodo usado en deleteByIdNetworkConnection()
     * @param idConnection tipo entero
     * @return lista de dispositivos electronicos donde su Fk connectio sea igual al parametro idConnection, puede retornar la lista vacia
     */
    public List<ElectronicDeviceDto> searchByIdConnection(int idConnection) {

        List<ElectronicDevice> listDevices = dispositivoElectronicoRepo.findAllByConnectionIdConnection(idConnection);       
        return mapperConverter(listDevices);
    }

    /**
     * Metodo usado en availableNetworks()
     * @return lista de tipo ElectronicDeviceDto, trae todos los dispositivos de la tabla, puede estar vacia
     */
    public List<ElectronicDeviceDto> searchAllDevices() {

        List<ElectronicDevice> listDevices = dispositivoElectronicoRepo.findAll();
        return mapperConverter(listDevices);
    }

    /**
     * Convierte una lista tipo ElectronicDevice a una de tipo ElectronicDeviceDto
     * @param listDevices
     * @return List<ElectronicDeviceDto>
     */
    public List<ElectronicDeviceDto> mapperConverter(List<ElectronicDevice> listDevices) {
        
        return listDevices.stream()
        .map(ElectronicDevice -> modelMapper.map(ElectronicDevice, ElectronicDeviceDto.class))
        .collect(Collectors.toList()); 
    }

    /**
     * Metodo usado en availableNetworks()
     * @return lista tipo NetworkConnectionDto, trae todo las networks de la tabla, puede estar vacia
     */
    public List<NetworkConnectionDto> searchAllNetworks() {

        List<NetworkConnection> listNetworks = networkConnectioRepo.findAll();
        return listNetworks.stream()
        .map(NetworkConnection -> modelMapper.map(NetworkConnection, NetworkConnectionDto.class))
        .collect(Collectors.toList());
    }    
}
