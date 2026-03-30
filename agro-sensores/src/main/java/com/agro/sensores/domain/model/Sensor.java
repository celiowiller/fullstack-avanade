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
	private String localizacao;
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
	
	// comportamentos do dominio
	public void ativar() {
		this.ativo = true;
	}
	
	public void desativar() {
		this.ativo = false;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
}
