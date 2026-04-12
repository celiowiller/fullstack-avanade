import { Component, inject, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { MaterialModule } from '../../shared/material/material.module';
import { UsuarioService } from '../../services/usuario.service';
import { UserRoleModel } from '../../models/user-role.model';

/**
  Componente de navegação principal (Header).
  Utiliza Signals para reagir ao estado de autenticação em tempo real.
 */
@Component({
  selector: 'app-header',
  standalone: true, // Importante para componentes modernos
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
  private servicoUsuario = inject(UsuarioService)
  private roteador = inject(Router)

  /* Signal computado: usuário está logado? */
  public estaLogado = computed(() => this.servicoUsuario.estaLogado())

  /* Signal computado: usuário é ADMIN? */  // ← ADICIONE ESTE SIGNAL
  public ehAdmin = computed(() => 
    this.servicoUsuario.estaLogado() && 
    this.servicoUsuario.obterRole() === UserRoleModel.ADMIN
  );

  sair(): void {
    this.servicoUsuario.logout();
    this.roteador.navigate(['/login'])
  }
}