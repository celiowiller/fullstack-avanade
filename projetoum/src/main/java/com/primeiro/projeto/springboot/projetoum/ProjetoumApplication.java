package com.primeiro.projeto.springboot.projetoum;
// este é o entry point/ponto de entrada da aplicação

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // annotation composta: esta instrução diz que temos uma classe
// de configuração do projeto
public class ProjetoumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoumApplication.class, args);
		// esta unica linha esta fazendo um "trabalho" enorme, aqui
		
		/*1. criando o application context: ou seja, iniciando o "conteiner" que guarda
		 * tudo aquilo que a aplicação precisa para funcionar
		 * 
		 * 2. iniciando o server embarcado: quando executamos a aplicação, esta
		 * faz com que o TomCat seja iniciado
		 * 
		 * processando argumentos: por exemplo, se passar qualquer parametro - como trocar
		 * de porta / da padrão para alguam custmomizado - o args assume este e leva esta informação 
		 * dentro do spring
		 * 
		 * */
	}

}
