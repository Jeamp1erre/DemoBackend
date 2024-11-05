package com.grupo1.demo.services;

import com.grupo1.demo.models.Consulta;
import com.grupo1.demo.models.Paciente;
import com.grupo1.demo.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAllByOrderByFechaConsultaDesc();
    }

    public Optional<Consulta> getConsultaById(Long id) {
        return consultaRepository.findById(id);
    }

    public List<Consulta> getConsultasByPaciente(Paciente paciente) {
        return consultaRepository.findByPacienteIdOrderByFechaConsultaDesc(paciente.getId());
    }

    public List<Consulta> getConsultasByFecha(LocalDateTime fecha) {
        return consultaRepository.findAllByFechaConsulta(fecha); 
    }

    public Consulta saveConsulta(Consulta consulta) {
        consulta.setFechaConsulta(LocalDateTime.now()); // Asigna la fecha y hora actual
        return consultaRepository.save(consulta);
    }

    public void deleteConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

    public Optional<Consulta> updateConsulta(Long id, Consulta updatedConsulta) {
        return consultaRepository.findById(id).map(existingConsulta -> {
            existingConsulta.setMotivoConsulta(
                Optional.ofNullable(updatedConsulta.getMotivoConsulta()).orElse(existingConsulta.getMotivoConsulta())
            );
            return consultaRepository.save(existingConsulta);
        });
    }
}
