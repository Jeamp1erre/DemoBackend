package com.grupo1.demo.services;

import com.grupo1.demo.models.Consulta;
import com.grupo1.demo.models.Diagnostico;
import com.grupo1.demo.repositories.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public Optional<Diagnostico> getDiagnosticoById(Long id) {
        return diagnosticoRepository.findById(id);
    }

    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoRepository.findAllByOrderByIdDesc();
    }
    

    public Diagnostico saveDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    public void deleteDiagnostico(Long id) {
        diagnosticoRepository.deleteById(id);
    }

    public Optional<Diagnostico> updateDiagnostico(Long id, Diagnostico updatedDiagnostico) {
        return diagnosticoRepository.findById(id).map(existingDiagnostico -> {
            existingDiagnostico.setNombreDoctor(
                Optional.ofNullable(updatedDiagnostico.getNombreDoctor()).orElse(existingDiagnostico.getNombreDoctor())
            );
            existingDiagnostico.setDescripcionDiagnostico(
                Optional.ofNullable(updatedDiagnostico.getDescripcionDiagnostico()).orElse(existingDiagnostico.getDescripcionDiagnostico())
            );
            return diagnosticoRepository.save(existingDiagnostico);
        });
    }
    
     public List<Diagnostico> getDiagnosticosByConsulta(Consulta consulta) {
        return diagnosticoRepository.findByConsulta(consulta); 
    }
}
