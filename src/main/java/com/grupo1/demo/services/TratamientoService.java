package com.grupo1.demo.services;

import com.grupo1.demo.models.Diagnostico;
import com.grupo1.demo.models.Tratamiento;
import com.grupo1.demo.repositories.TratamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TratamientoService {
    @Autowired
    private TratamientoRepository tratamientoRepository;

    public Optional<Tratamiento> getTratamientoById(Long id) {
        return tratamientoRepository.findById(id);
    }

    public Tratamiento saveTratamiento(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }

    public void deleteTratamiento(Long id) {
        tratamientoRepository.deleteById(id);
    }

     public Optional<Tratamiento> updateTratamiento(Long id, Tratamiento updatedTratamiento) {
        return tratamientoRepository.findById(id).map(existingTratamiento -> {
            existingTratamiento.setDescripcionTratamiento(
                Optional.ofNullable(updatedTratamiento.getDescripcionTratamiento()).orElse(existingTratamiento.getDescripcionTratamiento())
            );
            existingTratamiento.setDuracionDias(
                Optional.ofNullable(updatedTratamiento.getDuracionDias()).orElse(existingTratamiento.getDuracionDias())
            );
            return tratamientoRepository.save(existingTratamiento);
        });
    }

    public List<Tratamiento> getTratamientosByDiagnostico(Diagnostico diagnostico) {
        return tratamientoRepository.findByDiagnostico(diagnostico); 
    }
}
