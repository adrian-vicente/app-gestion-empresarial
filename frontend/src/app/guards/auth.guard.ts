import { CanActivateFn, Router } from '@angular/router';
import { Injectable, inject } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';

export const authGuard: CanActivateFn = (route, state) => {

  // Inyección de dependencias

  const authService = inject(AuthService);
  const router = inject(Router);

  // Método para comprobar si el usuario estaba autenticado previamente

  if(authService.estaAutenticado()) {
    return true;

  }

  // En caso de que no esté autenticado vamos a redirigir al login de la página

  return router.createUrlTree(['/login']);

};
