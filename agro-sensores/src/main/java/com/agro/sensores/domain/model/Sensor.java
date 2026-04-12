package com.agro.sensores.domain.model;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.exception.ValidacaoException;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Sensor {
    private String id;
    private String nome;
    private String localizacao; // No domínio está localizacao
    private TipoSensor tipo;
    private boolean ativo;
    
	public Sensor(
			String id, 
			String nome,
			String localizacao,
			TipoSensor tipo,
			boolean ativo
			) {
		
		// validações 
		if(nome == null || nome.isBlank()) {
			throw new ValidacaoException("Nome do sensor é obrigatorio!"); 
		}
		
		if(localizacao == null || localizacao.isBlank()) {
			throw new ValidacaoException("Localização é obrigatória!"); 
		}
		
		if(tipo == null) {
			throw new ValidacaoException("Tipo do sensor é obrigatorio!"); 
		}
		
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.tipo = tipo;
		this.ativo = ativo;
	}
	
	// *** EXTRA - MÉTODO NOVO: Permite que o UseCase atualize o estado atual do sensor
    public void alterarLocalizacao(String novaLocalizacao) {
        if(novaLocalizacao == null || novaLocalizacao.isBlank()) {
            throw new ValidacaoException("A nova localização não pode ser vazia!");
        }
        this.localizacao = novaLocalizacao;
    }
	
	// comportamentos do dominio
    public void ativar() { this.ativo = true; }
    public void desativar() { this.ativo = false; }

    public boolean isAtivo() { return ativo; }
}
