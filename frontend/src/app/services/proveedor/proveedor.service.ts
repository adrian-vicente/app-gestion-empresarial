import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Proveedor } from '../../models/Proveedor/Proveedor';
import { Ingreso } from '../../models/Ingreso/Ingreso';
import { ProveedorUpdate } from '../../models/Proveedor/ProveedorUpdate';
import { ProveedorCreate } from '../../models/Proveedor/ProveedorCreate';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  // Inyección de dependencias

  public http = inject(HttpClient);

  // Declaración de variables

  private apiUrl: string = "https://localhost:8080/api/proveedores";

  constructor() { }

  // Método para obtener todos los proveedores

  public obtenerProveedores(): Observable<Proveedor[]> {
    return this.http.get<Ingreso[]>(`${this.apiUrl}`);

  }

  // Método para modificar un proveedor existente

  public modificarProveedor(proveedor: ProveedorUpdate, proveedor_id: number): Observable<Proveedor> {
    return this.http.put<Proveedor>(`${this.apiUrl}/modificar/${proveedor_id}`, proveedor);

  }

  // Método para crear un nuevo proveedor

  public crearProveedor(proveedor: ProveedorCreate): Observable<Proveedor> {
    return this.http.post<Proveedor>(`${this.apiUrl}/crear`, proveedor);

  }

} // class
