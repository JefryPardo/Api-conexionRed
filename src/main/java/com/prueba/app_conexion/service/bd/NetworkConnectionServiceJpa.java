package com.prueba.app_conexion.service.bd;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.validation.Valid;

import com.prueba.app_conexion.config.exception.BadRequestException;
import com.prueba.app_conexion.config.exception.ErrorResp;
import com.prueba.app_conexion.config.exception.NotFoundException;
import com.prueba.app_conexion.dto.ElectronicDeviceDto;
import com.prueba.app_conexion.dto.NetworkConnectionDto;
import com.prueba.app_conexion.model.NetworkConnection;
import com.prueba.app_conexion.repository.NetworkConnectionRepository;
import com.prueba.app_conexion.service.IElectronicDeviceService;
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
    private IElectronicDeviceService serviceElectronicDevice;

    @Autowired
    private ModelMapper modelMapper;
    
    /**
	 * busca todo los network tipo NetworkConnection
	 * @return lista tipo NetworkConnectionDto, puede estar vacia.
	 */
    @Override
    public List<NetworkConnectionDto> searchAll() {

        List<NetworkConnection> listNetwork = networkConnectioRepo.findAll();
        if(listNetwork.size() > 0){

            return listNetwork.stream()
            .map(NetworkConnection -> modelMapper.map(NetworkConnection, NetworkConnectionDto.class))
            .collect(Collectors.toList());
        }else{
           
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
        if(network.isPresent()){

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

        List<ElectronicDeviceDto> allDevices = serviceElectronicDevice.searchByConnection(idConnection);
        if(allDevices.size() > 0){
            throw new NotFoundException("El network tiene dispositivos electronicos enlazados, primero elimine sus relaciones: "+allDevices);
        }else{

            try {
                networkConnectioRepo.deleteById(idConnection); 
                ErrorResp msg = new ErrorResp("DELETE id: "+idConnection,"Se borro con exito.","Con exito.");
                 return msg;
            } catch (Exception e) {
                ErrorResp msg = new ErrorResp("DELETE id: "+idConnection,"No se encontro ninguna conexion.","Sin Exito");
                return msg;
            }
        }         
    }

    /**
	 * busca las networks disponibles
	 * @return lista tipo NetworkConnectionDto, puede estar vacia
    */
    @Override
    public List<NetworkConnectionDto> availableNetworks() {
        
        List<ElectronicDeviceDto> allDevices = serviceElectronicDevice.searchAll();
		List<NetworkConnectionDto> allNetworks = searchAll();
        List<NetworkConnectionDto> listNetworks = new ArrayList<>();
        
        for (NetworkConnectionDto network : allNetworks) 
        {
            int count = 0; 
            for (ElectronicDeviceDto device : allDevices) 
            { 
                if(network.getIdConnection() == device.getConnection().getIdConnection()){
                    count+=1;
                } 
            }
            if(count < 3){
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

        if(networkDto.getTypeConnection() == 1){

            String[] arr = {"WEP","WPA","WPA2"};
            if(!Arrays.asList(arr).contains(networkDto.getTypeStandard())){

                throw new BadRequestException("Invalid standard for connection type. Expected: WEP, WPA, WPA2");
            }
            return save(networkDto);
        }else{

            return save(networkDto);
        }
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
}
