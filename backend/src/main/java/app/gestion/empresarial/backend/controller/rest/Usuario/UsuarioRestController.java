package app.gestion.empresarial.backend.controller.rest.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.gestion.empresarial.backend.dto.Usuario.UsuarioCreateDTO;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioDTO;
import app.gestion.empresarial.backend.mapper.UsuarioMapper;
import app.gestion.empresarial.backend.repository.UsuarioRepository;
import app.gestion.empresarial.backend.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    // Inyección de dependencias

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioRestController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    // Método para la creación de un nuevo usuario 

    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioDTO) throws Exception {
        UsuarioDTO usuarioCreado = usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioCreado);

    }

} // class