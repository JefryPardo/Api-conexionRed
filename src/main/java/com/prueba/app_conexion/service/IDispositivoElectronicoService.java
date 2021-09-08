package com.prueba.app_conexion.service;
import java.util.ArrayList;
import java.util.Optional;

import com.prueba.app_conexion.model.DispositivoElectronico;

public interface IDispositivoElectronicoService {

    ArrayList<DispositivoElectronico> buscarTodas();
    Optional<DispositivoElectronico> buscarPorId(int idDispositivoElectronico);
    DispositivoElectronico guardar( DispositivoElectronico dispositivoElectronico);
    Optional<DispositivoElectronico> buscarMac(String mac);
    boolean eliminar(Integer id);
    boolean validarSmartphoneWifi(DispositivoElectronico dispositivo);


    
    // List<DispositivoElectronico> buscarPorConexionRed(int conexionRed);   


}
