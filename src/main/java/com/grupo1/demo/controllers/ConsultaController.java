    package com.grupo1.demo.controllers;

    import com.grupo1.demo.models.Consulta;
    import com.grupo1.demo.models.Paciente;
    import com.grupo1.demo.services.ConsultaService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/consultas")
    public class ConsultaController {
        @Autowired
        private ConsultaService consultaService;

        @GetMapping
        public List<Consulta> getAllConsultas() {
            return consultaService.getAllConsultas();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Consulta> getConsultaById(@PathVariable Long id) {
            return consultaService.getConsultaById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/paciente/{pacienteId}")
        public List<Consulta> getConsultasByPaciente(@PathVariable Long pacienteId) {
            Paciente paciente = new Paciente();
            paciente.setId(pacienteId);
            return consultaService.getConsultasByPaciente(paciente);
        }

        @PostMapping
        public Consulta saveConsulta(@RequestBody Consulta consulta) {
            return consultaService.saveConsulta(consulta);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Consulta> updateConsulta(@PathVariable Long id, @RequestBody Consulta updatedConsulta) {
            return consultaService.updateConsulta(id, updatedConsulta)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
            consultaService.deleteConsulta(id);
            return ResponseEntity.noContent().build();
        }
    }
