package com.example.demo.entities;

import java.time.LocalDate;

import com.example.demo.enums.Especialidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "A especialidade é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especialidade especialidade;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Max(value = 130, message = "A idade não pode ser superior a 130 anos")
    @Column(nullable = false)
    private Integer idade;

    @NotNull(message = "Informe se possui plano de saúde")
    @Column(nullable = false)
    private Boolean possuiPlanoSaude;

    @Column(nullable = false)
    private LocalDate dataCadastro;
    
    public Paciente () {}
    public Paciente (String nome, Especialidade especialidade, Integer idade, Boolean possuiPlanoSaude,LocalDate dataCadastro) {
    	this.nome = nome;
    	this.especialidade = especialidade;
    	this.idade = idade;
    	this.possuiPlanoSaude = possuiPlanoSaude;
    	this.dataCadastro = dataCadastro;
    			
    }

    //prepersist faz com que antes de ir para o banco de dados as informações ela passe por aqui, dai ele faz uma "verificação" para a data de envio, para ser preciso (para não correr o risco de duplicar as pessoas no mesmo dia que está na regra de neógico)
    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Boolean getPossuiPlanoSaude() {
		return possuiPlanoSaude;
	}

	public void setPossuiPlanoSaude(Boolean possuiPlanoSaude) {
		this.possuiPlanoSaude = possuiPlanoSaude;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
    
    
}