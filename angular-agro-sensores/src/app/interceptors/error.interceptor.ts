import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const snackBar = inject(MatSnackBar)
  const router = inject(Router)

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let mensagemErro = 'Ocorreu um erro inesperado no sistema.';

      // Verifica se o erro veio do nosso GerenciadorDeErros do Spring (DetalheErro)
      if (error.error && error.error.mensagem) {
        mensagemErro = error.error.mensagem
      }

      switch (error.status) {
        case 400:
          // Erros de validação (ConstraintViolation ou RegraNegocioException)
          snackBar.open(`Erro de Validação: ${mensagemErro}`, 'Fechar', {
            duration: 5000,
            panelClass: ['error-snackbar']
          });
          break

        case 401:
          // Não autenticado (Token expirado ou inválido)
          snackBar.open('Sessão expirada. Por favor, faça login novamente.', 'Fechar', {
            duration: 5000
          })
          localStorage.removeItem('token')
          router.navigate(['/login'])
          break

        case 403:
          // Proibido (Usuário sem Role de ADMIN tentando deletar, por exemplo)
          snackBar.open('Acesso negado: Você não tem permissão para esta ação.', 'Fechar', {
            duration: 5000
          })
          break

        case 500:
          // Erro interno do servidor
          snackBar.open('Erro interno no servidor de telemetria. Tente mais tarde.', 'Fechar', {
            duration: 5000
          })
          break

        default:
          snackBar.open(mensagemErro, 'Fechar', {
            duration: 5000
          })
          break
      }

      return throwError(() => new Error(mensagemErro))
    })
  )
}