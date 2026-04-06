package com.agro.sensores.domain.exception;

// classe para tratar potenciais erros de validação de dados 
public class ValidacaoException extends RuntimeException {
	
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
	
	public ValidacaoException(String mensagem) {
		super(mensagem);
	}
}
