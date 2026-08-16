import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Gasto } from '../../models/Gasto/Gasto';

@Injectable({
  providedIn: 'root',
})
export class GastoService {
  // Inyección de dependencias

  private readonly http = inject(HttpClient);

  // Declaración de variables

  private readonly apiUrl: string = 'https://localhost:8080/api/gastos';
  constructor() {}

  // Método para obtener todos los gastos

  public obtenerGastos(): Observable<Gasto[]> {
    return this.http.get<Gasto[]>(`${this.apiUrl}`);

  }

  // Método para crear un nuevo gasto

  // Método para obtener un gasto por id

  // Método para modificar un gasto existente

  // Método para eliminar un gasto

} // class
