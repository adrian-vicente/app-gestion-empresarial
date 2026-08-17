import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { UsuarioUpdate } from '../../models/Usuario/UsuarioUpdate';
import { Observable, tap } from 'rxjs';
import { Usuario } from '../../models/Usuario/Usuario';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  // Inyección de dependencias

  private http = inject(HttpClient);
  private authService = inject(AuthService);

  // Declaración de variables

  private apiUrl: string = "http://localhost:8080/api/usuarios";
  private accessToken: string | null = this.authService.obtenerAccessToken();

  constructor() { }

  // Método para obtener usuario autenticado

  public obtenerUsuarioAutenticado(): Observable<Usuario> {
    const headers = new HttpHeaders({Authorization: `Bearer ${this.accessToken}`});
    return this.http.get<Usuario>(`${this.apiUrl}/me`, { headers } );

  }

  // Método para obtener todos los usuarios limitado a ADMIN

  // Método para obtener usuario a partir de un identificador

  // Método para obtener el usuario autenticado (AuthService.ts)

  // Método para obtener el usuario a partir del nombre

  // Método para obtener los usuarios activos

  // Método para obtener los usuarios a partir de un rol

  // Método para modificar los datos de un usuario existente

  public modificarUsuario(usuarioModificado: UsuarioUpdate): Observable<Usuario> {
    return this.http.put<Usuario>(`${this.apiUrl}/me`, usuarioModificado);

  }

} // class
