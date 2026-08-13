import { CategoriaGasto } from "../enums/CategoriaGasto";
import { MetodoPago } from "../enums/MetodoPago";

export interface GastoUpdate {

  // Declaración de atributos

  id: number;
  categoriaGasto: CategoriaGasto;
  metodoPago: MetodoPago;
  iva: number;
  total: number;
  nombre: string;
  descripcion: string;
  numeroFactura: string;

  // Declaración de atributos para las relaciones

  usuarioId: number;
  proveedorId: number;

} // interface
