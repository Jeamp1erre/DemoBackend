package com.grupo1.demo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consulta")
public class Consulta {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaConsulta;
    private String motivoConsulta;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    @JsonBackReference("PacienteConsulta")
    private Paciente paciente;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    @JsonManagedReference("ConsultaDiagnostico")
    private List<Diagnostico> diagnosticos = new ArrayList<>();

}
