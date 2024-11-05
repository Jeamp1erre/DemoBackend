package com.grupo1.demo.controllers;

import com.grupo1.demo.models.Diagnostico;
import com.grupo1.demo.models.Tratamiento;
import com.grupo1.demo.services.TratamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
public class TratamientoController {
    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping("/diagnostico/{diagnosticoId}")
    public List<Tratamiento> getTratamientosByDiagnostico(@PathVariable Long diagnosticoId) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setId(diagnosticoId);
        return tratamientoService.getTratamientosByDiagnostico(diagnostico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratamiento> getTratamientoById(@PathVariable Long id) {
        return tratamientoService.getTratamientoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tratamiento saveTratamiento(@RequestBody Tratamiento tratamiento) {
        return tratamientoService.saveTratamiento(tratamiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamiento> updateTratamiento(@PathVariable Long id, @RequestBody Tratamiento updatedTratamiento) {
        return tratamientoService.updateTratamiento(id, updatedTratamiento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTratamiento(@PathVariable Long id) {
        tratamientoService.deleteTratamiento(id);
        return ResponseEntity.noContent().build();
    }
}
