import { CategoriaIngreso } from "../enums/CategoriaIngreso";
import { MetodoPago } from "../enums/MetodoPago";

export interface IngresoCreate {

  // Declaración de atributos

  metodoPago: MetodoPago;
  categoriaIngreso: CategoriaIngreso;
  nombre: string;
  descripcion: string;
  iva: number;
  total: number;

}
