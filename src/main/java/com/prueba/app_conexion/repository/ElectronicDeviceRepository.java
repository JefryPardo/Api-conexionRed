package com.prueba.app_conexion.repository;

import java.util.List;
import java.util.Optional;
import com.prueba.app_conexion.model.ElectronicDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectronicDeviceRepository extends JpaRepository <ElectronicDevice,Integer> {
    
    Optional<ElectronicDevice> findByMac(String mac);
    List<ElectronicDevice> findByConnectionIdConnection(int idConnection);
    List<ElectronicDevice> findAllByConnectionIdConnection(int idNetworkConnection);
}
