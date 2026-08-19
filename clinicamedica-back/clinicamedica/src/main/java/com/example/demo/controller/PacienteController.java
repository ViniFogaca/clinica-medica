package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; // IMPORT ADICIONADO
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Paciente;
import com.example.demo.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes") 
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> criar(@RequestBody @Valid Paciente paciente) {
        Paciente novoPaciente = service.salvar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPaciente);
    }
    
    // Atualizar Paciente 
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody @Valid Paciente paciente) {
        return ResponseEntity.ok(service.atualizar(id, paciente));
    }

    // Deletar Paciente 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }
}