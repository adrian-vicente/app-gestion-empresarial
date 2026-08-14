import { Routes } from '@angular/router';

export const routes: Routes = [

  // Ruta que redirigirá al componente para iniciar sesión

  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: '*', redirectTo: 'login'},

  {
    path: 'login',
    loadComponent: () => import('./components/auth/login/login.component')
      .then(m => m.LoginComponent) },

  // Ruta que redigirá al dashboard de la aplicación

  {
    path: 'login',
    loadComponent: () => import('./components/base/dashboard/dashboard.component')
      .then(m => m.DashboardComponent)
  },

];
