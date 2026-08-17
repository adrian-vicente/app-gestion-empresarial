import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Gasto } from '../../models/Gasto/Gasto';
import { GastoCreate } from '../../models/Gasto/GastoCreate';
import { GastoUpdate } from '../../models/Gasto/GastoUpdate';

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

  public crearGasto(gastoNuevo: GastoCreate): Observable<Gasto> {
    return this.http.post<Gasto>(`${this.apiUrl}/crear`, gastoNuevo);

  }

  // Método para modificar un gasto existente

  public modificarGasto(gastoModificado: GastoUpdate, gasto_id: number): Observable<Gasto> {
    return this.http.put<Gasto>(`${this.apiUrl}/${gasto_id}`, gastoModificado);

  }

} // class
