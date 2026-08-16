import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Login } from '../../models/Login/Login';
import { TokenResponse } from '../../models/Login/TokenReponse';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Inyección de dependencias

  private readonly http = inject(HttpClient);

  // Declaración de atributos

  private readonly apiUrl: string = 'http://localhost:8080/auth';
  private readonly ACCESS_TOKEN_KEY: string = 'accessToken';
  private readonly REFRESH_TOKEN_KEY: string = 'refreshToken';

  constructor() {}

  // Método para iniciar sesión

  public login(loginDTO: Login): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.apiUrl}/login`, loginDTO).pipe(
      tap((response) => {
        this.guardarTokens(response);
      }),
    );
  }

  // Método para almacenar los tokens

  protected guardarTokens(response: TokenResponse): void {
    localStorage.setItem(this.ACCESS_TOKEN_KEY, response.accessToken);
    localStorage.setItem(this.REFRESH_TOKEN_KEY, response.refreshToken);
  }

  // Método para obtener el token de acceso

  protected obtenerAccessToken(): string | null {
    return localStorage.getItem(this.ACCESS_TOKEN_KEY);
  }

  // Método para refrescar el token actual

  protected obtenerRefreshToken(): string | null {
    return localStorage.getItem(this.REFRESH_TOKEN_KEY);
  }

  // Método para cerrar sesión en la aplicación

  public logout(): void {
    localStorage.removeItem(this.ACCESS_TOKEN_KEY);
    localStorage.removeItem(this.REFRESH_TOKEN_KEY);
  }

  // Método para validar si un usuario está autenticado o no

  public estaAutenticado(): boolean {
    return this.obtenerAccessToken() != null;
  }

  // Método para cambiar la password del usuario

} // class
