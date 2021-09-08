package com.prueba.app_conexion.service.bd;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.prueba.app_conexion.model.ConexionRed;
import com.prueba.app_conexion.model.DispositivoElectronico;
import com.prueba.app_conexion.repository.ConexionRedRepository;
import com.prueba.app_conexion.service.IConexionRedService;
import com.prueba.app_conexion.service.IDispositivoElectronicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

@Service
@Primary
public class ConexionRedServiceJpa implements IConexionRedService{

    @Autowired
    private ConexionRedRepository conexionRedRepo;

    @Autowired
    private IDispositivoElectronicoService serviceDispositivoElectronico;



    @Override
    public ArrayList<ConexionRed> buscarTodas() {
        return (ArrayList<ConexionRed>) conexionRedRepo.findAll();
    }

    @Override
    public Optional<ConexionRed> buscarPorId(int idConexionRed) {
        return conexionRedRepo.findById(idConexionRed);
    }

    @Override
    public ConexionRed guardar(ConexionRed conexion_red) {
        return conexionRedRepo.save(conexion_red); 
    }

    @Override
    public boolean eliminar(Integer id) {
        try {
            conexionRedRepo.deleteById(id); 
            return true;
        } catch (Exception e) {
            return false;
        } 
    }



    // Redes disponibles //retorna las redes que tienen menos de 3 dispositivos
    @Override
    public ArrayList<ConexionRed> buscarDisponibles() {
        
        ArrayList<DispositivoElectronico> dispositivos = serviceDispositivoElectronico.buscarTodas();
		ArrayList<ConexionRed> redes = buscarTodas();

        ArrayList<ConexionRed> newListRed = new ArrayList<ConexionRed>(); 

        for (int i=0;i<redes.size();i++) { 
			int contador = 0;     
			for (int j=0;j<dispositivos.size();j++) {   
				if(redes.get(i).getId() == dispositivos.get(j).getConexion().getId()){
					contador+=1;
				}
			}
			if(contador < 3){
				newListRed.add(redes.get(i));
			}
		}

        return newListRed;	
    }    
    
}
