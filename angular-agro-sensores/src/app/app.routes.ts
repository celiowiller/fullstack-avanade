import { Routes } from '@angular/router';
import { CadastroComponent } from './components/usuario/cadastro/cadastro.component';
import { LoginComponent } from './components/usuario/login/login.component';
import { authGuard } from './guards/auth.guard';
import { SensorListComponent } from './components/sensores/sensor-list/sensor-list.component';

export const routes: Routes = [
    // ---- ROTAS PUBLICAS ---- 
    // ACESSIVEIS SEM QUALQUER TOKEN
    {path: 'cadastro', component: CadastroComponent},
    {path: 'login', component: LoginComponent},

     // --- ROTAS RESTRITAS  ---

    {
        path: 'sensores',
        canActivate: [authGuard],
        children: [
        { path: '', component: SensorListComponent }
        ]
    }

];
