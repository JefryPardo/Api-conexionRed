package com.prueba.app_conexion.service.bd;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.prueba.app_conexion.config.exception.BadRequestException;
import com.prueba.app_conexion.config.exception.NotFoundException;
import com.prueba.app_conexion.dto.ElectronicDeviceDto;
import com.prueba.app_conexion.dto.NetworkConnectionDto;
import com.prueba.app_conexion.model.ElectronicDevice;
import com.prueba.app_conexion.repository.ElectronicDeviceRepository;
import com.prueba.app_conexion.service.IElectronicDeviceService;
import com.prueba.app_conexion.service.INetworkConnectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Primary
@Validated
public class ElectronicDeviceServiceJpa implements IElectronicDeviceService {

    @Autowired
    private ElectronicDeviceRepository dispositivoElectronicoRepo;

    @Autowired
    @Lazy
    private INetworkConnectionService serviceNetwordConnection;

    @Autowired
    private ModelMapper modelMapper;

    /**
	 * Guarda el dispositivo electronico o enseñar los errores presentados.
     * Se hace el respectivo modelMapper.
	 * @param electronicDeviceDto
	 * @return json del objecto guardado ó de lo contrario el error presentado
	 */
    @Override
    public ElectronicDeviceDto save(ElectronicDeviceDto electronicDeviceDto) {
        try {

            ElectronicDevice device = modelMapper.map(electronicDeviceDto, ElectronicDevice.class);
            device = dispositivoElectronicoRepo.save(device);
        
            return modelMapper.map(device,ElectronicDeviceDto.class);
        } catch (Exception e) {

            throw new NotFoundException("Error: variables no validas.  error info:"+e.getMessage());
        } 
    }

    /**
	 * Se encarga de buscar todos los dispositivos.
	 * @return lista en formato json de los dispositivos encontrados, puede estar vacia
	 */
    @Override
    public List<ElectronicDeviceDto> searchAll() {

        List<ElectronicDevice> listEleDevice = dispositivoElectronicoRepo.findAll();

        if(listEleDevice.size()==0){

            throw new NotFoundException("No se encontro ningun dispositivo1.");
        }else{

            return listEleDevice.stream()
            .map(ElectronicDevice -> modelMapper.map(ElectronicDevice, ElectronicDeviceDto.class))
            .collect(Collectors.toList());
        }
    }

    /**
	 * Busca mediante el id del dispositivo electronico
	 * @param idDispositivoElectronico
	 * @return el objecto en formato json si lo encuentra, de lo contrario retorna null
	 */
    @Override
    public ElectronicDeviceDto searchById(int idDispositivoElectronico) {
        
        Optional<ElectronicDevice> device = dispositivoElectronicoRepo.findById(idDispositivoElectronico);
        if(device.isPresent()){
            return modelMapper.map(device.get(), ElectronicDeviceDto.class);
        }
        return null;
    }

    /**
	 * guarda el dispositivo electronico mediante algunas validaciones de por medio
	 * @param electronicDeviceDto
	 * @return el objecto en formato json si lo guarda, de lo contrario retorna los errores presentados
	 */
    @Override
    public ElectronicDeviceDto saveElectronicDevice(ElectronicDeviceDto electronicDeviceDto) {        
        
        if(searchMac(electronicDeviceDto.getMac()) == null) {

            List<NetworkConnectionDto> listNetworks = serviceNetwordConnection.availableNetworks();
            if(available(listNetworks, electronicDeviceDto)) {
                if(electronicDeviceDto.getTypeDevice().equals("smartphone") && electronicDeviceDto.getConnection().getTypeConnection() == 1) {
                    
                    List<ElectronicDeviceDto> allElectronicDeviceDtos = searchDevices();
                    if(allElectronicDeviceDtos.size()>0){
                        if(permittedSmartphone(allElectronicDeviceDtos, electronicDeviceDto)) {
                        
                            return save(electronicDeviceDto);
                        }else {
        
                            throw new BadRequestException("La red tipo wifi solo permite un dispositivo tipo smartphone");
                        }
                    }else{
                        
                        return save(electronicDeviceDto);
                    }
                }else {
    
                    return save(electronicDeviceDto);
                }
            }else {
    
                throw new BadRequestException("red no valida o disponible.");
            }
        }else {

            throw new BadRequestException("Mac no disponible.");  
        }
    }
    
    public List<ElectronicDeviceDto> searchDevices() {

        List<ElectronicDevice> listEleDevice = dispositivoElectronicoRepo.findAll();

        return listEleDevice.stream()
        .map(ElectronicDevice -> modelMapper.map(ElectronicDevice, ElectronicDeviceDto.class))
        .collect(Collectors.toList());
    }

    /**
	 * busca por el campo mac tipo string
	 * @param mac
	 * @return el objecto en formato json si lo encuentra, de lo contrario retorna null
	 */
    @Override
    public ElectronicDeviceDto searchMac(String mac) {

        Optional<ElectronicDevice> device = dispositivoElectronicoRepo.findByMac(mac);
        if(device.isPresent()){
            return modelMapper.map(device.get(), ElectronicDeviceDto.class);
        }
        return null;
    }   

    /**
	 * busca por el campo mac tipo string
	 * @param idElectronicDevice
	 * @return el objecto en formato json si lo encuentra, de lo contrario retorna null
	 */
    @Override
    public boolean deleteByIdElectronicDevice(Integer idElectronicDevice) {
        try {
            dispositivoElectronicoRepo.deleteById(idElectronicDevice);  
            return true;
        } catch (Exception e) {
            return false;
        } 
    } 
    
    /**
	 * metodo requerido en el save, se validad sí la network tiene un dispositovo tipo smartphone asociado
	 * @param allElectronicDeviceDtos electronicDeviceDto
	 * @return el metodo se llama permite smartphone ? retorna false ó true
	 */
    public boolean permittedSmartphone(List<ElectronicDeviceDto> allElectronicDeviceDtos, ElectronicDeviceDto electronicDeviceDto) {

        for (ElectronicDeviceDto devices : allElectronicDeviceDtos) 
        { 
            if(devices.getTypeDevice().equals("smartphone") && devices.getConnection().getIdConnection() == electronicDeviceDto.getConnection().getIdConnection()){
                return false;
            }
        }
        return true;
    }

    /**
	 * busca si la network ingresada esta en la lista listNetworks, con esto nos damos cuenta que la network esta disponible si retorna true
	 * @param allElectronicDeviceDtos electronicDeviceDto
	 * @return true si se encuntra, de lo contrario false
	 */
    public boolean available(List<NetworkConnectionDto> listNetworks, ElectronicDeviceDto dispositivoElectronico) {

        for (NetworkConnectionDto network : listNetworks) 
        { 
            if(network.getIdConnection() == dispositivoElectronico.getConnection().getIdConnection()){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ElectronicDeviceDto> searchByConnection(int idConnection) {

        List<ElectronicDevice> listDevices = dispositivoElectronicoRepo.findAllByConnectionIdConnection(idConnection);       
        if(listDevices.size()==0){

            throw new NotFoundException("No se encontro ningun dispositivo2.");
        }else{

            return listDevices.stream()
            .map(ElectronicDevice -> modelMapper.map(ElectronicDevice, ElectronicDeviceDto.class))
            .collect(Collectors.toList());
        }
    }    
}