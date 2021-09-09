package com.prueba.app_conexion.service.bd;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public List<ConexionRed> buscarTodas() {
        return (List<ConexionRed>) conexionRedRepo.findAll();
    }

    @Override
    public Optional<ConexionRed> buscarPorId(int idConexionRed) {
        return conexionRedRepo.findById(idConexionRed);
    }

    @Override
    public ConexionRed guardar(ConexionRed conexionRed) {
        return conexionRedRepo.save(conexionRed); 
    }

    /**
     * Se encarga de borrar una coenxión de la base de datos, por id
     * 
     * @param id Corresponde al id de la conexión a eliminar
     * @return retorna false en caso de que no sea posible borrar el registro. true en caso contrario.
     */
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
    public List<ConexionRed> buscarDisponibles() {
        
        List<DispositivoElectronico> dispositivos = serviceDispositivoElectronico.buscarTodas();
		List<ConexionRed> redes = buscarTodas();

        List<ConexionRed> newListRed = new ArrayList<>();
        
        System.out.println(newListRed);

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
