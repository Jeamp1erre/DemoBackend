package com.grupo1.demo.repositories;

import com.grupo1.demo.models.HistoriaMedica;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriaMedicaRepository extends JpaRepository<HistoriaMedica, Long> {
    Optional<HistoriaMedica> findByPacienteId(Long pacienteId);
}
