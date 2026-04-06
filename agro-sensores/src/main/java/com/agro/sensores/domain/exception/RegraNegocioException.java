package com.agro.sensores.domain.exception;

// classe que "trata" exceção de erros potenciais para regra de negocio
public class RegraNegocioException  extends RuntimeException{
	
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
	
	
	// definir o construtor da classe
	public RegraNegocioException(String mensagem) {
		// aqui, dentro do metodo construtor da nossa classe
		// vamos fazer referencia a nossa superclasse - RuntimeException
		super(mensagem);
	}
}
