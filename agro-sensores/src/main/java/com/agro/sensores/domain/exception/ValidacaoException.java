package com.agro.sensores.domain.exception;

// classe para tratar potenciais erros de validação de dados 
public class ValidacaoException extends RuntimeException {
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
