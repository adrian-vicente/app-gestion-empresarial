package app.gestion.empresarial.backend.controller.rest;

import app.gestion.empresarial.backend.service.ProveedorService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.gestion.empresarial.backend.dto.Proveedor.ProveedorCreateDTO;
import app.gestion.empresarial.backend.dto.Proveedor.ProveedorDTO;
import app.gestion.empresarial.backend.dto.Proveedor.ProveedorUpdateDTO;
import app.gestion.empresarial.backend.exception.ProveedorNotFoundException;
import app.gestion.empresarial.backend.model.Proveedor;
import app.gestion.empresarial.backend.repository.ProveedorRepository;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorRestController {

    // Inyección de dependencias

    private final ProveedorService proveedorService;
    private final ProveedorRepository proveedorRepository;
    public ProveedorRestController(ProveedorRepository proveedorRepository, ProveedorService proveedorService) {
        this.proveedorRepository = proveedorRepository;
        this.proveedorService = proveedorService; 

    }

    // Método para obtener todos los proveedores

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProveedorDTO>> obtenerProveedores() {
        return ResponseEntity.ok(proveedorService.obtenerProveedores());

    }

    // Método para obtener proveedor a partir del id 

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProveedorDTO> obtenerProveedorId(@PathVariable Long id) throws ProveedorNotFoundException {
        return ResponseEntity.ok(proveedorService.obtenerProveedorId(id));
    }

    // Método para crear un nuevo proveedor

    @PostMapping("/crear")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProveedorDTO> crearNuevoProveedor(@RequestBody @Valid ProveedorCreateDTO proveedorCreateDTO) {
        return ResponseEntity.ok(proveedorService.crearNuevoProveedor(proveedorCreateDTO));

    }

    // Método para modificar los datos de proveedor existente 

    @PutMapping("/modificar/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProveedorDTO> modificarProveedorExistente(@RequestBody @Valid ProveedorUpdateDTO dto, @PathVariable Long id) throws ProveedorNotFoundException {
        return ResponseEntity.ok(proveedorService.modificarProveedorExistente(dto, id));

    }

} // class