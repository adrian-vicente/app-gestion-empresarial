package app.gestion.empresarial.backend.controller.rest.Auth;

import org.springframework.web.bind.annotation.RestController;

import app.gestion.empresarial.backend.service.UsuarioService;

@RestController
public class AuthRestController {

    // Inyección de dependencias 

    private final UsuarioService usuarioService;
    public AuthRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método para iniciar sesión

} // class