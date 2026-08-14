import { Routes } from '@angular/router';
import { DashboardComponent } from './components/base/dashboard/dashboard.component';
import { authGuard } from './guards/auth.guard';
import { LoginComponent } from './components/auth/login/login.component';

export const routes: Routes = [
  // Ruta que redirigirá al componente para iniciar sesión

  { path: '', redirectTo: 'login', pathMatch: 'full' },

  {
    path: 'login',
    component: LoginComponent

  },

  // Ruta que redigirá al dashboard. Ruta protegida. Es necesario estar autenticado para acceder.

  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
  },

  { path: '*', redirectTo: 'dashboards' },
];
