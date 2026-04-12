import { Routes } from '@angular/router';
import { CadastroComponent } from './components/usuario/cadastro/cadastro.component';
import { LoginComponent } from './components/usuario/login/login.component';
import { authGuard } from './guards/auth.guard';
import { adminGuard } from './guards/admin.guard';

// Componentes de Sensores
import { SensorListComponent } from './components/sensores/sensor-list/sensor-list.component';
import { SensorDetailComponent } from './components/sensores/sensor-detail/sensor-detail.component';
import { SensorFormComponent } from './components/sensores/sensor-form/sensor-form.component';

// Novos Componentes de Telemetria e Visão Geral
import { DashboardComponent } from './components/sensores/dashboard/dashboard.component';
import { TelemetriaFormComponent } from './components/sensores/telemetria-form/telemetria-form.component';

export const routes: Routes = [
    // ---- ROTAS PÚBLICAS ---- 
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'cadastro', component: CadastroComponent },
    { path: 'login', component: LoginComponent },

    // --- ROTAS RESTRITAS (Qualquer usuário logado) ---
    {
        path: 'sensores',
        canActivate: [authGuard],
        children: [
            { path: '', component: SensorListComponent },
            // Visão individual do sensor e seu histórico temporal
            { path: 'detalhes/:id', component: SensorDetailComponent }
        ]
    },

    {
        path: 'telemetria',
        canActivate: [authGuard],
        children: [
            // Visão geral de todos os sensores com leituras (UseCase de agregação)
            { path: 'dashboard', component: DashboardComponent },
            // Simulador para envio de novos dados (TelemetriaController)
            { path: 'simulador', component: TelemetriaFormComponent }
        ]
    },

    // --- ROTAS RESTRITAS (Nível: APENAS ADMIN) ---
    {
        path: 'configuracoes',
        canActivate: [authGuard, adminGuard],
        children: [
            { path: 'novo-sensor', component: SensorFormComponent },
            { path: 'editar-sensor/:id', component: SensorFormComponent }
        ]
    },

    // Rota de fallback para evitar erros de navegação
    { path: '**', redirectTo: 'login' }
];