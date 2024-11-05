package com.grupo1.demo.services;

import com.grupo1.demo.models.Paciente;
import com.grupo1.demo.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAllByOrderByIdDesc();
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> searchPaciente(String nombre, String apellido) {
        return pacienteRepository.findByNombreIgnoringAccentsAndCase(nombre, apellido);
    }

    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Optional<Paciente> updatePaciente(Long id, Paciente updatedPaciente) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            existingPaciente.setNombre(
                Optional.ofNullable(updatedPaciente.getNombre()).orElse(existingPaciente.getNombre())
            );
            existingPaciente.setApellido(
                Optional.ofNullable(updatedPaciente.getApellido()).orElse(existingPaciente.getApellido())
            );
            existingPaciente.setDni(
                Optional.ofNullable(updatedPaciente.getDni()).orElse(existingPaciente.getDni())
            );
            existingPaciente.setFechaNacimiento(
                Optional.ofNullable(updatedPaciente.getFechaNacimiento()).orElse(existingPaciente.getFechaNacimiento())
            );
            existingPaciente.setGenero(
                Optional.ofNullable(updatedPaciente.getGenero()).orElse(existingPaciente.getGenero())
            );
            existingPaciente.setTelefono(
                Optional.ofNullable(updatedPaciente.getTelefono()).orElse(existingPaciente.getTelefono())
            );
            existingPaciente.setEmail(
                Optional.ofNullable(updatedPaciente.getEmail()).orElse(existingPaciente.getEmail())
            );
            return pacienteRepository.save(existingPaciente);
        });
    }
    
}
