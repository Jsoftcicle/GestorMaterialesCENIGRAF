package com.CENIGRAF.GestorMateriales.ServiceCRUD;

import com.CENIGRAF.GestorMateriales.ModelBD.AmbienteModel;
import com.CENIGRAF.GestorMateriales.Repository.AmbienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {

    @Autowired
    AmbienteRepository ambienteRepository;

    public List<AmbienteModel> getAll(){
        return ambienteRepository.getAll();
    }

    public Optional<AmbienteModel> getAmbienteModel(int id){
        return ambienteRepository.getAmbienteModel(id);
    }

    public AmbienteModel save(AmbienteModel ambienteModel){
        if (ambienteModel.getIdAmbiente()== null){
            return ambienteRepository.save(ambienteModel);
        }else{
            Optional<AmbienteModel> ambaux=ambienteRepository.getAmbienteModel(ambienteModel.getIdAmbiente());
            if (ambaux.isEmpty()){
                return ambienteRepository.save(ambienteModel);
            }else{
                return ambienteModel;
            }

        }
    }

    public AmbienteModel update(AmbienteModel ambienteModel){
        if (ambienteModel.getIdAmbiente()!= null){
            Optional<AmbienteModel> Am=ambienteRepository.getAmbienteModel(ambienteModel.getIdAmbiente());
            if (Am.isEmpty()){
                if (ambienteModel.getIdAmbiente()!= null){
                    Am.get().setIdAmbiente(ambienteModel.getIdAmbiente());
                }
                if (ambienteModel.getNombre()!= null){
                    Am.get().setNombre(ambienteModel.getNombre());
                }
                if (ambienteModel.getDescripcion()!= null){
                    Am.get().setDescripcion(ambienteModel.getDescripcion());
                }
                ambienteRepository.save(Am.get());
                return Am.get();
            }else{
                return ambienteModel;
            }
        }else {
            return ambienteModel;
        }
    }

    public boolean deleteAmbienteModel(int id){
        Boolean dAm=getAmbienteModel(id).map(ambienteModel -> {
            ambienteRepository.delete(ambienteModel);
            return true;
        }).orElse(false);
        return dAm;
    }

}