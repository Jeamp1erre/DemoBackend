package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Consulta;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findAllByOrderByFechaConsultaDesc();
    List<Consulta> findByPacienteIdOrderByFechaConsultaDesc(Long pacienteId);
    List<Consulta> findAllByFechaConsulta(LocalDateTime fecha);

}
