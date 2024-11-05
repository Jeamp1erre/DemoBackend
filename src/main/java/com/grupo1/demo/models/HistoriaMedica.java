    package com.grupo1.demo.models;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    
    import jakarta.persistence.*;
    import lombok.*;

    @Data
    @Builder
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "historia_medica")
    public class HistoriaMedica {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String antecedentesMedicos;
        private String cirugiasAnteriores;
        private String alergias;
        private String antecedentesFamiliares;

        @OneToOne
        @JoinColumn(name = "paciente_id", referencedColumnName = "id")
        @JsonBackReference("PacienteHistoria") 
        private Paciente paciente;
    }
