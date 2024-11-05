package com.grupo1.demo.repositories;

import com.grupo1.demo.models.Diagnostico;
import com.grupo1.demo.models.Tratamiento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    List<Tratamiento> findByDiagnostico(Diagnostico diagnostico);
}
