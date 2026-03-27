package com.primeiro.projeto.springboot.projetoum.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// este é um "pacote" java que "abriga/junta" todos os recursos em comum


/* abaixo, temos uma classe comum Java! então, para que esta classe se torne 
 * um controller de fluxo de dados, precisamos estabelecer este contexto;
 * para este proposito precisamos fazer o seguinte:
 * precisamos fazer uso de uma annotation especifica: @RestController: esta é 
 * a annotation que "dará" à esta classe, o "papel" de controller/controlador
 * de fluxo de dados; ao "transformar" esta classe num controller, estamos dizendo
 * que, a partir de agora, podemos manusear/manipular as requisições HTTP necessarias
 * GET, POST, PUT, DELETE
 * 
 *  @RequestMapping("/api/simples") : estamos usando esta anotação para definir nosso
 *  endpoint-base; portanto, este é o endpoint principal deste controller
 * 
 * @GetMapping: é a anotação que "mapeia(indica/define)" para onde, em especifico, em 
 * especifico a requisição get dev ser feita; portanto, est eé o endpoint especifico 
 * deste controller
 * 
 * ResponseEntity<String> : este é o recurso que dá, ao método olaMundo(), a possibilidade
 * de retornar uma resposta para uma requisição HTTP que foi feita
 * neste caso, se tudo correr bem, o retorno será o status 200(ok) informando que a requisição
 * foi feita e foi aceita!!!!
 *
 * 
 */

//**** então, nossa requisição será para o seguinte endpoint/endereço: 
// http://localhost:8080/api/simples/ola

@RestController // aqui, dizemos que esta classe é um controller

@RequestMapping("/api/simples") // aqui, temos a annotation que determina para "onde"
// as requisições HTTP devem ser feitas: portanto a annotation ele indica o "endereço"
// para o qual as requisições, que o controller fará, devem ser feitas.

public class SimplesController {
	
	@GetMapping("/ola") // esta annotation determina onde é o "ponto-final" do nosso "endreço"
	// para a requisição; ou seja, agora, nosso endpoint esta completo
	
	public ResponseEntity<String> olaMundo(){
		// temos de definir a expressão de retorno do método
		return ResponseEntity.ok("Olá mundo Springboot! A galera, aqui, vai dominar");
	}
}

// resumindo: o controller está "controlando" a requisição HTTP GET que está sendo feita 
// pela API para exibir uma mensagem no browser
