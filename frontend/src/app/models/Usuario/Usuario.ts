import { Rol } from "../enums/Rol";

export interface Usuario {

  // Declaración de atributos

  id: number;
  nombre: string;
  email: string;
  edad: number;
  rol: Rol;
  activo: boolean;
  fechaCreacionUsuario: Date;
  fechaUltimoLogin: Date;
  fechaUltimaActualizacion: Date;
  telefono: String;

} // interface
