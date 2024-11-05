package com.grupo1.demo.services;

import com.grupo1.demo.models.HistoriaMedica;
import com.grupo1.demo.models.Paciente;
import com.grupo1.demo.repositories.HistoriaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoriaMedicaService {
    @Autowired
    private HistoriaMedicaRepository historiaMedicaRepository;

    public Optional<HistoriaMedica> getHistoriaMedicaById(Long id) {
        return historiaMedicaRepository.findById(id);
    }

    public Optional<HistoriaMedica> getHistoriaMedicaByPacienteId(Long pacienteId) {
        return historiaMedicaRepository.findByPacienteId(pacienteId);
    }

    public HistoriaMedica saveHistoriaMedica(HistoriaMedica historiaMedica) {
        return historiaMedicaRepository.save(historiaMedica);
    }
public void deleteHistoriaMedica(Long id) {
    HistoriaMedica historiaMedica = historiaMedicaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Historia médica no encontrada con id: " + id));

    // Desvincular historia médica del paciente
    Paciente paciente = historiaMedica.getPaciente();
    if (paciente != null) {
        paciente.setHistoriaMedica(null);
    }

    historiaMedicaRepository.delete(historiaMedica);
}

    
    public Optional<HistoriaMedica> updateHistoriaMedica(Long id, HistoriaMedica updatedHistoriaMedica) {
        return historiaMedicaRepository.findById(id).map(existingHistoriaMedica -> {
            existingHistoriaMedica.setAntecedentesMedicos(
                Optional.ofNullable(updatedHistoriaMedica.getAntecedentesMedicos()).orElse(existingHistoriaMedica.getAntecedentesMedicos())
            );
            existingHistoriaMedica.setCirugiasAnteriores(
                Optional.ofNullable(updatedHistoriaMedica.getCirugiasAnteriores()).orElse(existingHistoriaMedica.getCirugiasAnteriores())
            );
            existingHistoriaMedica.setAlergias(
                Optional.ofNullable(updatedHistoriaMedica.getAlergias()).orElse(existingHistoriaMedica.getAlergias())
            );
            existingHistoriaMedica.setAntecedentesFamiliares(
                Optional.ofNullable(updatedHistoriaMedica.getAntecedentesFamiliares()).orElse(existingHistoriaMedica.getAntecedentesFamiliares())
            );
            return historiaMedicaRepository.save(existingHistoriaMedica);
        });
    }
    
}
