package app.gestion.empresarial.backend.controller.rest;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.gestion.empresarial.backend.dto.Ingreso.IngresoCreateDTO;
import app.gestion.empresarial.backend.dto.Ingreso.IngresoDTO;
import app.gestion.empresarial.backend.exception.IngresoAlreadyExistsException;
import app.gestion.empresarial.backend.exception.IngresoNotFoundException;
import app.gestion.empresarial.backend.service.IngresoService;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoRestController {

    // Inyección de dependencias 

    private final IngresoService ingresoService;
    public IngresoRestController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;

    }

    // Método para obtener todos los ingresos 

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<IngresoDTO>> obtenerTodosLosIngresos() {
        return ResponseEntity.ok(ingresoService.obtenerTodosLosIngresos());

    }

    // Método para obtener ingreso mediante id 

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<IngresoDTO> obtenerIngresoPorId(@PathVariable Long id) throws IngresoNotFoundException {
        return ResponseEntity.ok(ingresoService.obtenerIngresoPorId(id));

    }

    // Método para crear un nuevo ingreso 

    @PostMapping("/crear")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<IngresoDTO> crearNuevoIngreso(@RequestBody IngresoCreateDTO ingresoCreateDTO) throws IngresoAlreadyExistsException {
        return ResponseEntity.ok(ingresoService.crearNuevoIngreso(ingresoCreateDTO));

    }
    
    // Método para modificar un ingreso existente

} // class