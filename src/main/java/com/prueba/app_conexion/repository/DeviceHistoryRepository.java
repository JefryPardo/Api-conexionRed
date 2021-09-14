package com.prueba.app_conexion.repository;

import java.util.List;
import com.prueba.app_conexion.model.DeviceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceHistoryRepository extends JpaRepository<DeviceHistory,Integer> {
        
    List<DeviceHistory> findByMac(String mac);
}
