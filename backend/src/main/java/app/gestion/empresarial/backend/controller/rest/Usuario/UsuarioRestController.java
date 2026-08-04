package app.gestion.empresarial.backend.controller.rest.Usuario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.gestion.empresarial.backend.dto.Usuario.UsuarioDTO;
import app.gestion.empresarial.backend.exception.UsuarioException.UsuarioNotFoundException;
import app.gestion.empresarial.backend.repository.UsuarioRepository;
import app.gestion.empresarial.backend.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    // Inyección de dependencias 

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;

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
        UsuarioDTO usuario = usuarioService.findUserById(id);
        return ResponseEntity
            .ok(usuario);

    } // findUserById

} // class