package com.agro.sensores.domain.exception;

// classe que "trata" exceção de erros potenciais para regra de negocio
public class RegraNegocioException  extends RuntimeException{
	// definir o construtor da classe
	public RegraNegocioException(String mensagem) {
		// aqui, dentro do metodo construtor da nossa classe
		// vamos fazer referencia a nossa superclasse - RuntimeException
		super(mensagem);
	}
}
