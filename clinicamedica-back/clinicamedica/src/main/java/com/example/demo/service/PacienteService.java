package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Paciente;
import com.example.demo.enums.Especialidade;
import com.example.demo.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        // Regra da Pediatria 
        if (paciente.getEspecialidade() == Especialidade.PEDIATRIA && paciente.getIdade() > 14) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pacientes encaminhados para Pediatria devem ter no máximo 14 anos.");
        }

        // Regra de Nome Duplicado no mesmo dia 
        if (repository.existsByNomeIgnoreCaseAndDataCadastro(paciente.getNome(), LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um paciente com este nome cadastrado no dia de hoje.");
        }

        return repository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente dadosAtualizados) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));
        
        // Aplica a validação de Pediatria se for o caso
        if (dadosAtualizados.getEspecialidade() == Especialidade.PEDIATRIA && dadosAtualizados.getIdade() > 14) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pacientes encaminhados para Pediatria devem ter no máximo 14 anos.");
        }

        paciente.setNome(dadosAtualizados.getNome());
        paciente.setEspecialidade(dadosAtualizados.getEspecialidade());
        paciente.setIdade(dadosAtualizados.getIdade());
        paciente.setPossuiPlanoSaude(dadosAtualizados.getPossuiPlanoSaude());

        return repository.save(paciente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.");
        }
        repository.deleteById(id);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }
}