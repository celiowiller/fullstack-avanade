import { Component, inject, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { MaterialModule } from '../../shared/material/material.module';
import { UsuarioService } from '../../services/usuario.service';

/**
 * Componente de navegação principal (Header).
 * Utiliza Signals para reagir ao estado de autenticação em tempo real.
 */
@Component({
  selector: 'app-header', 
  imports: [
    CommonModule, 
    MaterialModule, 
    RouterLink, 
    RouterLinkActive
  ],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  // Injeção dos serviços necessários para a lógica do Header
  private servicoUsuario = inject(UsuarioService);
  private roteador = inject(Router);

  /**
   * SIGNAL COMPUTADO: estaLogado()
   * Este sinal observa o estado dentro do UsuarioService. 
   * Sempre que o serviço executar o .set() no sinal de login, 
   * este componente será notificado automaticamente para atualizar o HTML.
   */
  public estaLogado = computed(() => this.servicoUsuario.estaLogado());

  /**
   * Método para encerrar a sessão.
   * Aciona a limpeza do localStorage e do Signal no serviço,
   * garantindo que os links de navegação desapareçam imediatamente.
   */
  sair(): void {
    this.servicoUsuario.logout();
    this.roteador.navigate(['/login']);
  }
}