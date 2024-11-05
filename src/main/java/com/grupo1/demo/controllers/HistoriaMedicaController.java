package com.grupo1.demo.controllers;

import com.grupo1.demo.models.HistoriaMedica;
import com.grupo1.demo.services.HistoriaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historias-medicas")
public class HistoriaMedicaController {
    @Autowired
    private HistoriaMedicaService historiaMedicaService;

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaMedica> getHistoriaMedicaById(@PathVariable Long id) {
        return historiaMedicaService.getHistoriaMedicaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistoriaMedica saveHistoriaMedica(@RequestBody HistoriaMedica historiaMedica) {
        return historiaMedicaService.saveHistoriaMedica(historiaMedica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriaMedica> updateHistoriaMedica(@PathVariable Long id, @RequestBody HistoriaMedica updatedHistoriaMedica) {
        return historiaMedicaService.updateHistoriaMedica(id, updatedHistoriaMedica)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriaMedica(@PathVariable Long id) {
        historiaMedicaService.deleteHistoriaMedica(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<HistoriaMedica> getHistoriaMedicaByPacienteId(@PathVariable Long pacienteId) {
    return historiaMedicaService.getHistoriaMedicaByPacienteId(pacienteId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

}
