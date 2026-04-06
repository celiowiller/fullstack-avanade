package com.agro.sensores.domain.exception;

// classe que "trata" exceção de erros potenciais para algum recurso que, eventualmente,
// pode não ser encontrado
public class RecursoNaoEncontradoException extends RuntimeException {
	/*
	 Essa linha é um "RG" (Registro Geral) da sua classe.
     Quando o Java precisa transformar um objeto 
     em "bits" (para salvar em um arquivo ou enviar pela rede), 
     ele "carimba" esse número no objeto.
	 */
	// Adicionando esta linha (o valor 1L é o padrão)
    private static final long serialVersionUID = 1L;
    
    /*
     private static final: Garante que o número seja uma constante única 
     da classe e que ninguém possa alterá-lo.
	 long: É o tipo do dado (um número inteiro longo).
	 serialVersionUID: É o nome padrão que o Java procura 
	 para identificar a versão da classe.

	  1L: É o número da versão (o "L" indica que é do tipo long).
     */
    
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