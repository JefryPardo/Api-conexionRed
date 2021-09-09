package com.prueba.app_conexion.repository;

import java.util.List;
import java.util.Optional;

import com.prueba.app_conexion.model.DispositivoElectronico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoElectronicoRepository extends JpaRepository <DispositivoElectronico,Integer> {
    
    Optional<DispositivoElectronico> findByMac(String x);
    List<DispositivoElectronico> findAllByConexionId(int id);
}
