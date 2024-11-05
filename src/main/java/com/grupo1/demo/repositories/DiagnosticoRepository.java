package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Consulta;
import com.grupo1.demo.models.Diagnostico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
    List<Diagnostico> findAllByOrderByIdDesc();
       List<Diagnostico> findByConsulta(Consulta consulta);
    
}
