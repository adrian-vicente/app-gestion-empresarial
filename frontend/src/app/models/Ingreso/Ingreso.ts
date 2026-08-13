import { CategoriaIngreso } from "../enums/CategoriaIngreso";
import { MetodoPago } from "../enums/MetodoPago";

export interface Ingreso {

  // Declaración de atributos

  id: number;
  metodoPago: MetodoPago;
  categoriaIngreso: CategoriaIngreso;
  nombre: string;
  descripcion: string;
  iva: number;
  total: number;

}
