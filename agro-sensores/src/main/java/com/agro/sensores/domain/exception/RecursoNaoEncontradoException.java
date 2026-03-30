package com.agro.sensores.domain.exception;

// classe que "trata" exceção de erros potenciais para algum recurso que, eventualmente,
// pode não ser encontrado
public class RecursoNaoEncontradoException extends RuntimeException {
	// definir o construtos da classe
	public RecursoNaoEncontradoException(String mensagem) {
		// aqui, dentro do metodo construtor da nossa classe
		// vamos fazer referencia a nossa superclasse - RuntimeException
		super(mensagem);
	}
}

// quando temos o tratamento de exceptions via RuntimeException:
// temos uma unchecked exception -> o compilador NÃO NOS OBRIGA O TRATAMENTO
// usamos se for necessario!

// Exception: esta é uma checked exception -> o compilador NOS OBRIGA A TRATAR 
// ou seja, nós precisamos de algo como, por exemplo - um try/catch -> throws


// para esta "camada"  o uso do RuntimeExceptioné muito importante pois 
// nos da a possiblidade de tratar erros que não são, necessariamente, do sistema
// e sim, algo pontual referente aos dados.