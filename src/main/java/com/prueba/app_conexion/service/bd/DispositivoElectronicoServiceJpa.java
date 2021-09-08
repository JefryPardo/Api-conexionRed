package com.prueba.app_conexion.service.bd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.prueba.app_conexion.model.DispositivoElectronico;
import com.prueba.app_conexion.repository.DispositivoElectronicoRepository;
import com.prueba.app_conexion.service.IDispositivoElectronicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
public class DispositivoElectronicoServiceJpa implements IDispositivoElectronicoService{

    @Autowired
    private DispositivoElectronicoRepository dispositivoElectronicoRepo;

    @Override
    public ArrayList<DispositivoElectronico> buscarTodas() {
        return ( ArrayList<DispositivoElectronico> ) dispositivoElectronicoRepo.findAll();
    }

    @Override
    public Optional<DispositivoElectronico> buscarPorId(int idDispositivoElectronico) {
        return dispositivoElectronicoRepo.findById(idDispositivoElectronico);
    }

    @Override
    public DispositivoElectronico guardar(DispositivoElectronico dispositivoElectronico) {
        return dispositivoElectronicoRepo.save(dispositivoElectronico);    
    }

    @Override
    public Optional<DispositivoElectronico> buscarMac(String mac_id) {
        return dispositivoElectronicoRepo.findByMac(mac_id);
    }

    

    @Override
    public boolean eliminar(Integer id) {
        try {
            dispositivoElectronicoRepo.deleteById(id);  
            return true;
        } catch (Exception e) {
            return false;
        } 
    }


    // Esta red tipo wifi tiene un smartphone?
    @Override
    public boolean validarSmartphoneWifi(DispositivoElectronico dispositivo) {
        List<DispositivoElectronico> allDispositivos = buscarTodas();

        int contador = 0;
        for (int j=0;j<allDispositivos.size();j++) {   
            if(dispositivo.getConexion().getId() == allDispositivos.get(j).getConexion().getId()){
                if(allDispositivos.get(j).getConexion().getTipo().equals("smartphone")){
                    contador+=1;
                }
            }
        }

        if(contador > 0){
            return true;
        }else{
            return false;
        }

    }    
    
}



// @Override
// public List<DispositivoElectronico> buscarPorConexionRed(int conexionRed) {
//     return dispositivoElectronicoRepo.findAllByConexionId(conexionRed);
// }