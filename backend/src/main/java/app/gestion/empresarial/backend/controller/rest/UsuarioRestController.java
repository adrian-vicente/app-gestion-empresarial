package app.gestion.empresarial.backend.controller.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import app.gestion.empresarial.backend.dto.UsuarioDTO;
import app.gestion.empresarial.backend.dto.UsuarioUpdateDTO;
import app.gestion.empresarial.backend.exception.UsuarioException.UsuarioNotFoundException;
import app.gestion.empresarial.backend.repository.UsuarioRepository;
import app.gestion.empresarial.backend.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    // Inyección de dependencias 

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    // Método para obtener todos los ususarios (Limitado a ADMIN)

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtener")
    public ResponseEntity<List<UsuarioDTO>> findAllUsers() {
        List<UsuarioDTO> usuariosDTOs = usuarioService.findAllUsers();
        return ResponseEntity
            .ok(usuariosDTOs);

    } // findAllUsers

    // Método para obtener usuario a partir de un identificador 

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtener/{id}")
    public ResponseEntity<UsuarioDTO> findUserById(@PathVariable Long id) throws UsuarioNotFoundException {
        return ResponseEntity.ok(usuarioService.findUserById(id));

    } // findUserById

    // Método para obtener usuario autenticado en la aplicación

    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> getAuthenticatedUser() throws UsuarioNotFoundException {
        return ResponseEntity.ok(usuarioService.getAuthenticatedUser());

    } // getAuthenticatedUser

    // Método para obtener usuario a partir del nombre 

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{nombre}")
    public ResponseEntity<UsuarioDTO> findUserByNombre(@PathVariable String nombre) throws UsuarioNotFoundException {
        return ResponseEntity.ok(usuarioService.findUserByNombre(nombre));

    } // findUserByNombre

    // Método para obtener todos los usuarios activos 

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtener/activos")
    public ResponseEntity<List<UsuarioDTO>> getActiveUsers() {
        return ResponseEntity.ok(usuarioService.getActiveUsers());

    } // getActiveUsers

    // Método para obtener todos los usuarios a partir de un rol 

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/obtener/rol")
    public ResponseEntity<List<UsuarioDTO>> getUsersByRol(String rol) {
        return ResponseEntity.ok(usuarioService.getUsersByRol(rol));
    }

    // Método para modificar los datos de un usuario existente 

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioDTO> updateUser(@RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuarioUpdateDTO));

    }

} // class