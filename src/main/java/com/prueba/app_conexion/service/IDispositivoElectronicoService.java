package com.prueba.app_conexion.service;
import java.util.List;
import java.util.Optional;

import com.prueba.app_conexion.model.DispositivoElectronico;

public interface IDispositivoElectronicoService {

    List<DispositivoElectronico> buscarTodas();
    Optional<DispositivoElectronico> buscarPorId(int idDispositivoElectronico);
    DispositivoElectronico guardar( DispositivoElectronico dispositivoElectronico);
    Optional<DispositivoElectronico> buscarMac(String mac);
    boolean eliminar(Integer id);
    boolean validarSmartphoneWifi(DispositivoElectronico dispositivo);
    
}
