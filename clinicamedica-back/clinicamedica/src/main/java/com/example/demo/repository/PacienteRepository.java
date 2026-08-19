package com.example.demo.repository;

import com.example.demo.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
    // Verifica se já existe um paciente com esse nome na data informada
    boolean existsByNomeIgnoreCaseAndDataCadastro(String nome, LocalDate dataCadastro);
}