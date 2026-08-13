import { CategoriaGasto } from "../enums/CategoriaGasto";
import { MetodoPago } from "../enums/MetodoPago";

export interface GastoCreate {

  // Declaración de atributos

  id: number;
  categoriaGasto: CategoriaGasto;
  metodoPago: MetodoPago;
  nombre: String;
  descripcion: String;
  numeroFactura: String;
  iva: number;
  total: number;

} // interface
