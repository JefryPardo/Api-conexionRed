package com.prueba.app_conexion.service.bd;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.prueba.app_conexion.config.exception.BadRequestException;
import com.prueba.app_conexion.config.exception.ErrorResp;
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
        ElectronicDevice device = modelMapper.map(electronicDeviceDto, ElectronicDevice.class);
        device = dispositivoElectronicoRepo.save(device);
        
        return modelMapper.map(device,ElectronicDeviceDto.class); 
    }

    /**
	 * Se encarga de buscar todos los dispositivos.
	 * @return lista en formato json de los dispositivos encontrados, puede estar vacia
	 */
    @Override
    public List<ElectronicDeviceDto> searchAll() {

        List<ElectronicDevice> listEleDevice = dispositivoElectronicoRepo.findAll();

        if(listEleDevice.size()==0) {

            throw new NotFoundException("No se encontro ningun dispositivo1.");
        }else {

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
        if(device.isPresent()) {

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
        
        List<ElectronicDeviceDto> allElectronicDeviceDtos = searchDevices();
        if(allElectronicDeviceDtos.size() == 0) {

            return save(electronicDeviceDto);
        }else {

            if(availableMac(electronicDeviceDto)) { 

                List<NetworkConnectionDto> listNetworks = serviceNetwordConnection.availableNetworks();
                if(available(listNetworks, electronicDeviceDto)) {

                    if(fieldsValid(electronicDeviceDto)) {
                        
                        if(permittedSmartphone(allElectronicDeviceDtos, electronicDeviceDto)) {
                        
                            return save(electronicDeviceDto);
                        }else {
        
                            throw new BadRequestException("La red tipo wifi solo permite un dispositivo tipo smartphone");
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
    }

    /**
     * Valida si el objecto electronicDeviceDto de tipo ElectronicDeviceDto en sus campos getTypeDevice = smartphone &&  getConnection.getTypeConnection == 1
     * @param electronicDeviceDto
     * @return true si la condicion se da, de lo contrario false
     */
    public boolean fieldsValid(ElectronicDeviceDto electronicDeviceDto) {

        return (electronicDeviceDto.getTypeDevice().equals("smartphone") && electronicDeviceDto.getConnection().getTypeConnection() == 1);
    }

    /**
     * validad si el dispositivo a guardar tiene una mac validad y no repetida
     * @param electronicDeviceDto
     * @return  retorna true si la mac es validad, de lo contrario false
     */
    public boolean availableMac(ElectronicDeviceDto electronicDeviceDto) {

        return (searchMac(electronicDeviceDto.getMac()) == null);
    }
    
    /**
     * Busca todos los dispositivos de la entidad ElectronicDevice y retorna una lista de tipo List<ElectronicDeviceDto>, puede estar vacia
     * @return List<ElectronicDeviceDto>
     */
    public List<ElectronicDeviceDto> searchDevices() {

        List<ElectronicDevice> listDevices = dispositivoElectronicoRepo.findAll();

        return mapperConverter(listDevices);
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
    public ErrorResp deleteByIdElectronicDevice(Integer idElectronicDevice) {

        try {

            dispositivoElectronicoRepo.deleteById(idElectronicDevice);  
                return new ErrorResp("Con exito.","Se pudo borrar el dispositivo electronico con id: "+idElectronicDevice,"200 OK");
        } catch (Exception e) {

            return new ErrorResp("Sin exito","No se pudo borrar el dispositivo electronico con id: "+idElectronicDevice,"404 Not Found");
        } 
    } 
    
    /**
	 * Método requerido en el save, se validad sí la network tiene un dispositovo tipo smartphone 
     * asociado
     * 
	 * @param allElectronicDeviceDtos electronicDeviceDto Corresponde al listado completo de dispositivos elecctrónicos.
	 * @return 
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
	 * Busca si la network ingresada esta en la lista listNetworks, con esto nos damos cuenta que 
     * la network esta disponible si retorna true
     * 
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

    /**
     * 
     */
    @Override
    public List<ElectronicDeviceDto> searchByConnection(int idConnection) {

        List<ElectronicDevice> listDevices = dispositivoElectronicoRepo.findAllByConnectionIdConnection(idConnection);       
        if(listDevices.size()==0){

            throw new NotFoundException("No se encontro ningun dispositivo.");
        }else{

            return mapperConverter(listDevices);
        }
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
}