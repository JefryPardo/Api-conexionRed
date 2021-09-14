package com.prueba.app_conexion.repository;

import java.util.List;
import com.prueba.app_conexion.model.ConnectionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionHistoryRepository extends JpaRepository<ConnectionHistory,Integer> {
    
    List<ConnectionHistory> findByNetwork(int idNetworkConnection);
}
