package com.agro.sensores.domain.strategy;

import org.springframework.stereotype.Component;

import com.agro.sensores.domain.enums.TipoSensor;
import com.agro.sensores.domain.exception.RegraNegocioException;
import com.agro.sensores.domain.model.Leitura;

// implementação d a validação para sensor de solo
// aqui será definido como validar leitura para sensor do tipo SOLO


// criando um objeto: aqui, nós controlamos o objeto 
// MinhaClasse objeto = new MinhaClasse();
// acima temos um objeto - que é a instancia da classe!!! quando instanciamos um objeto e
// esta instancia é unica - ou seja, não preciso instanciar novamente, então temos um 
// "objeto singleton"



@Component // aqui, estamos dizendo: "crie e gerencie essa classe, automaticamente"
//significa não precisamos usar a palavra reservada new para este proposito

// @Component
// public classe MinhaClasse{}: aqui, temos uma instancia da classe MinhaClasse, sem o uso da 
// palavra reservada new - porque estamos utilizando a annotation @Component;
// agora o Spring criou o objeto; o Spring guarda o objeto;
// o Spring entrega o objeto  quando precisamos fazer uso

//então o Spring "controla" este sigleton - registrando-o como um Bean para a aplicação;
//o que é um Bean? R.: é um objeto criado e gerenciado pelo Spring.
public class ValidadorSoloStrategy implements ValidadorSensorStrategy {
	// Verficar se esse validador é para sensor de solo
	public boolean suporta(TipoSensor tipo) {
		return tipo == TipoSensor.SOLO;
	}
	
	// executar a validação da leitura
	public void validar(Leitura leitura) {
		// validação da faixa de umidade
		if(leitura.getValor() < 0 || leitura.getValor() > 100) {
			// lançamento de exceção de regra de negocio
			throw new RegraNegocioException("Valor de umidade do solo invalida!");
		}
	}
}
