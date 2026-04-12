import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MaterialModule } from '../../../shared/material/material.module';
import { UsuarioService } from '../../../services/usuario.service';

@Component({
  selector: 'app-login', 
  imports: [CommonModule, ReactiveFormsModule, MaterialModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // titulo componente
  tituloComp: string = 'Login'
  private formularioConstrutor = inject(FormBuilder)
  private servicoUsuario = inject(UsuarioService)
  private roteador = inject(Router)

  loginFormulario = this.formularioConstrutor.group({
    login: ['', [Validators.required]],
    senha: ['', [Validators.required, Validators.minLength(6)]]
  });

  aoSubmeter(): void {
    if (this.loginFormulario.valid) {
      const credenciais = this.loginFormulario.value;
      this.servicoUsuario.login(credenciais as any).subscribe({
        next: () => {
          this.roteador.navigate(['/sensores'])
        },
        error: (erro) => {
          console.error('Erro capturado no componente de login:', erro)
        }
      })
    }
  }
}