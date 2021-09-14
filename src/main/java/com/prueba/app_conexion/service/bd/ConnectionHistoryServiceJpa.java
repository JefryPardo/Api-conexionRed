package com.prueba.app_conexion.service.bd;

import java.util.List;
import java.util.stream.Collectors;
import com.prueba.app_conexion.dto.ConnectionHistoryDto;
import com.prueba.app_conexion.model.ConnectionHistory;
import com.prueba.app_conexion.repository.ConnectionHistoryRepository;
import com.prueba.app_conexion.service.IConnectionHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ConnectionHistoryServiceJpa implements IConnectionHistoryService {

    @Autowired
    private ConnectionHistoryRepository connectionHistoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ConnectionHistoryDto> searchConnectionHistoryByIdNetworkConnection(int idNetworkConnection) {
        
        List<ConnectionHistory> listNetworks =  connectionHistoryRepo.findByNetwork(idNetworkConnection);
        
        return listNetworks.stream()
        .map(ConnectionHistory -> modelMapper.map(ConnectionHistory, ConnectionHistoryDto.class))
        .collect(Collectors.toList());
    }    
}
