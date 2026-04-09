// 1. aqui, SEMPRE SERÁ NECESSARIO FAZER IMPORTAÇÕES DOS RECURSOS QUE CADA COMPONENTE DEVERÁ USAR PARA O SEU PLENO FUNCIONAMENTO

import { Component } from '@angular/core'; // este é o recurso necessa´rio para "transformar" um arquivo/classe .ts - comum - num componente Angular 

// 2. este é o recurso que permite/possibilita, se necessario, estabelecer "rotas/navegação" para a navegação entre componentes do projeto
import { RouterOutlet } from '@angular/router';

import { HeaderComponent } from './components/header/header.component'; // Importe o novo componente
// 3. abaixo, temos o DECORATOR(que, na maioria das vezes, é indicado com o caractere @)
// este decorator diz que: "agora, a classe .ts é parte de um componente angular"
@Component({
  selector: 'app-root', // esta é a propriedade - selector - que dá ao componente; seu nome é app-root! portanto, o "conteúdo" que esta sendo "injetado" dentro de index.html tem origem aqui, neste componente! 

// 4. além de importar os recursos para o pleno funcionamento do componente, alguns destes recursos precisam, tambem, serem "registrados" como elementos/recursos "disponiveis" para uso; na maioria das vezes estes registros serão para recursos de modulo - com o sufixo "module"
  imports: [RouterOutlet, HeaderComponent],

  templateUrl: './app.component.html', // aqui está o "endereço" do arquivo .html/template/view que pertence/compõe este componente

  styleUrl: './app.component.css' // aqui está o "endereço" do arquivo .css/estilos/atributos de estilo que pertence/compõe este componente
})
// 5. 
export class AppComponent {
  // toda a vez que criamos um projeto angular o componente app.component - com seus respectivos arquivos - é criado, por padrão! E, na "camada lógica" - aquivo .ts - do
  // componente, sempre há uma variavel padrão que recebe como valor o titulo da aplicação 
  title = 'angular-agro-sensores';
}

// esse, sendo o "componente principal" do projeto é necessario que, de forma direta ou indireta, TODOS OS COMPONENTES "conversem" com este aqui!!!! 
