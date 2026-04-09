// este é o arquivo responsavel pela configuração de recursos necessarios 
// para o pleno funcionamento do projeto angular
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

// então, precisamos adicionar/configurar os seguintes recursos
import { provideHttpClient, withInterceptors } from '@angular/common/http'; // provê todos os recursos necessarios para a construção das requisições http
import { authInterceptor } from './interceptors/auth.interceptor';// este é um recurso de fundamental importancia para o processo de navegação do usuario, uma vez que ele esteja autenticado para este proposito


export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        authInterceptor
      ])
    )
  ]
};
