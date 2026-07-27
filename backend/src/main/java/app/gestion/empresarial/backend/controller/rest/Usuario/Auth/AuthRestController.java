package app.gestion.empresarial.backend.controller.rest.Usuario.Auth;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.gestion.empresarial.backend.dto.Usuario.UsuarioCreateDTO;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioDTO;
import app.gestion.empresarial.backend.repository.UsuarioRepository;
import app.gestion.empresarial.backend.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
public class AuthRestController {

} // class