package app.gestion.empresarial.backend.controller.rest.Auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.gestion.empresarial.backend.dto.Login.LoginDTO;
import app.gestion.empresarial.backend.dto.Login.TokenResponseDTO;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioCreateDTO;
import app.gestion.empresarial.backend.service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    // Inyección de dependencias

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    // Método para iniciar sesión 

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody @Valid LoginDTO login) {
        return authService.login(login);        

    }

    // Método para crear un nuevo usuario 

    @PostMapping("/register")
    public TokenResponseDTO register(@RequestBody @Valid UsuarioCreateDTO registerUser) {
        return authService.register(registerUser);
    }
    

} // class