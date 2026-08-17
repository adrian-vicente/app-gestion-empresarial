import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Injectable, inject } from '@angular/core';
import { UsuarioService } from '../../../services/usuario/usuario.service';
import { Usuario } from '../../../models/Usuario/Usuario';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  // Inyección de dependencias

  private usuarioService: UsuarioService = inject(UsuarioService);

  constructor() {}

  // Declaración de variables

  public usuario: Usuario | null = null;
  public errorUsuarioAutenticado!: string;

  // Método para obtener el usuario actual al cargar el componente

  ngOnInit(): void {
    this.usuarioService.obtenerUsuarioAutenticado().subscribe({
      next: (usuarioAutenticado) => { this.usuario = usuarioAutenticado},
      error: (error_api) => {
        console.error(error_api.message);
        this.errorUsuarioAutenticado = error_api.message;
      }
    });

  } // ngOnInit

} // class
