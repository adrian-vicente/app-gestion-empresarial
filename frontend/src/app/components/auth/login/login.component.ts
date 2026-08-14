import { CommonModule } from '@angular/common';
import { Component, Injectable, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';
import { Login } from '../../../models/Login/Login';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  // Inyección de dependencias

  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  // Declaración de atributos

  private email: string = "";
  private password: string = "";
  private error: string = "";

  // Método para iniciar sesión en la aplicación

  protected iniciarSesion(): void {
    this.error = '';
    const login: Login = { email: this.email, password: this.password };

    // Lanzar el método del servicio para iniciar sesión

    this.authService.login(login).subscribe({
      next: () => { this.router.navigate(['/dashboard']) },
      error: (error) => {
        console.error(error);
        this.error = "El email o la password introducida no son correctas." }
    });

  } // iniciarSesion()

} // class
