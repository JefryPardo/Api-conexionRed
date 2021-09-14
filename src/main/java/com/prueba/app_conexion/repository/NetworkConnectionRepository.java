package com.prueba.app_conexion.repository;

import com.prueba.app_conexion.model.NetworkConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkConnectionRepository extends JpaRepository<NetworkConnection,Integer> {}