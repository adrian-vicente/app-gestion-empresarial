package app.gestion.empresarial.backend.controller.rest;

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

import app.gestion.empresarial.backend.dto.Gasto.GastoCreateDTO;
import app.gestion.empresarial.backend.dto.Gasto.GastoDTO;
import app.gestion.empresarial.backend.dto.Gasto.GastoUpdateDTO;
import app.gestion.empresarial.backend.exception.GastoAlreadyExistsException;
import app.gestion.empresarial.backend.exception.GastoNotFoundException;
import app.gestion.empresarial.backend.service.GastoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gastos")
public class GastoRestController {

    // Inyección de dependencias

    private final GastoService gastoService;
    public GastoRestController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    // Método para obtener todos los gastos

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<GastoDTO>> obtenerTodosLosGastos() {
        return ResponseEntity.ok(gastoService.obtenerTodosLosGastos());

    }

    // Método para obtener gasto a partir del identificador

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GastoDTO> obtenerGastoPorId(@PathVariable Long id) throws GastoNotFoundException {
        return ResponseEntity.ok(gastoService.obtenerGastoPorId(id));
    }

    // Método para crear un nuevo gasto 

    @PostMapping("/crear")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GastoDTO> crearNuevoGasto(@RequestBody @Valid GastoCreateDTO gastoCreateDTO) throws GastoAlreadyExistsException {
        return ResponseEntity.ok(gastoService.crearNuevoGasto(gastoCreateDTO));
        
    }

    // Método para modificar un gasto existente 

    @PutMapping("/modificar/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GastoDTO> modificarNuevoGasto(@RequestBody @Valid GastoUpdateDTO gastoUpdateDTO, @PathVariable Long gasto_id) throws GastoAlreadyExistsException {
        return ResponseEntity.ok(gastoService.modificarGastoExistente(gastoUpdateDTO, gasto_id));
        
    }

} // class