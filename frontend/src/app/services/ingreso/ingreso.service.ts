import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingreso } from '../../models/Ingreso/Ingreso';

@Injectable({
  providedIn: 'root'
})
export class IngresoService {

  // Inyección de dependencias

  private http = inject(HttpClient);

  // Declaración de variables y constructor de la clase

  private apiUrl: string = "https://localhost:8080/api/ingresos";

  constructor() { }

  // Método para obtener todos los ingresos

  public obtenerIngresos(): Observable<Ingreso[]> {
    return this.http.get<Ingreso[]>(`${this.apiUrl}`);

  }

  // Método para modificar un ingreso

  public modificarIngreso(ingresoModificado: Ingreso, ingreso_id: number): Observable<Ingreso> {
    return this.http.put<Ingreso>(`${this.apiUrl}/${ingreso_id}`, ingresoModificado);

  }

} // class
