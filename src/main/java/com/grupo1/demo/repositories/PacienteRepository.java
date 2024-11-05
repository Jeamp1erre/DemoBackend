package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByOrderByIdDesc();
    
    @Query("SELECT p FROM Paciente p WHERE " +
           "LOWER(REPLACE(REPLACE(REPLACE(p.nombre, 'á', 'a'), 'é', 'e'), 'í', 'i')) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
           "OR LOWER(REPLACE(REPLACE(REPLACE(p.apellido, 'á', 'a'), 'é', 'e'), 'í', 'i')) LIKE LOWER(CONCAT('%', :apellido, '%'))")
    List<Paciente> findByNombreIgnoringAccentsAndCase(@Param("nombre") String nombre, @Param("apellido") String apellido);
}
