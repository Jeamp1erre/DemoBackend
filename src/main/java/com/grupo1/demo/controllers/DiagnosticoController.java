package com.grupo1.demo.controllers;

import com.grupo1.demo.models.Diagnostico;
import com.grupo1.demo.models.Consulta;
import com.grupo1.demo.services.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {
    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping
    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoService.getAllDiagnosticos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> getDiagnosticoById(@PathVariable Long id) {
        return diagnosticoService.getDiagnosticoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diagnostico saveDiagnostico(@RequestBody Diagnostico diagnostico) {
        return diagnosticoService.saveDiagnostico(diagnostico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> updateDiagnostico(@PathVariable Long id, @RequestBody Diagnostico updatedDiagnostico) {
        return diagnosticoService.updateDiagnostico(id, updatedDiagnostico)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnostico(@PathVariable Long id) {
        diagnosticoService.deleteDiagnostico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consulta/{consultaId}")
    public List<Diagnostico> getDiagnosticosByConsulta(@PathVariable Long consultaId) {
        Consulta consulta = new Consulta();
        consulta.setId(consultaId);
        return diagnosticoService.getDiagnosticosByConsulta(consulta);
    }
}
