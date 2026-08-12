package app.gestion.empresarial.backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.gestion.empresarial.backend.dto.LoginDTO;
import app.gestion.empresarial.backend.dto.RefreshTokenDTO;
import app.gestion.empresarial.backend.dto.TokenResponseDTO;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioCreateDTO;
import app.gestion.empresarial.backend.exception.InvalidTokenException;
import app.gestion.empresarial.backend.exception.UsuarioAlreadyExistsException;
import app.gestion.empresarial.backend.exception.UsuarioNotFoundException;
import app.gestion.empresarial.backend.mapper.UsuarioMapper;
import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.model.enums.Rol;
import app.gestion.empresarial.backend.repository.UsuarioRepository;
import app.gestion.empresarial.backend.security.JwtService;

@Service
public class AuthService {
    
    // Inyección de dependencias

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Método para iniciar sesión 
    
    @Transactional(readOnly = true)
    public TokenResponseDTO login(LoginDTO loginDTO) {

        // Delegar la autenticación a spring security

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password())
        );

        // Obtener el usuario autenticado desde la base de datos

        Usuario usuario = usuarioRepository.findByEmail(loginDTO.email())
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado a ningún usuario con email: " + loginDTO.email()));

        // Generar los tokens con el usuario autenticado 

        String accessToken = jwtService.generateAccessToken(usuario);
        String refreshToken = jwtService.generateRefreshToken(usuario);

        // Devolver la respuesta con los tokens generado 

        return new TokenResponseDTO(accessToken, refreshToken, "Bearer");

    } // login

    // Método para registrar un nuevo usuario 

    @Transactional
    public TokenResponseDTO register(UsuarioCreateDTO usuarioCreateDTO) throws UsuarioAlreadyExistsException {

        // Método para validar que no exista el email 

        if(usuarioRepository.existsByEmail(usuarioCreateDTO.getEmail())) {
            throw new UsuarioAlreadyExistsException("Ya existe un usuario con el email: " + usuarioCreateDTO.getEmail());
        }

        // Convertir el DTO en entidad para guardarlo en la bbdd (La password la cifra el mapper)

        Usuario usuario = usuarioMapper.toEntity(usuarioCreateDTO);
        
        // Por defecto la app la usará una persona (El administrador)

        usuario.setRol(Rol.USUARIO);

        // Guardar el usuario en la base de datos y generar los tokens de acceso

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        String accessToken = jwtService.generateAccessToken(usuarioGuardado);
        String refreshToken = jwtService.generateRefreshToken(usuarioGuardado);

        // Devolver el TokenResponseDTO

        return new TokenResponseDTO(accessToken, refreshToken, "Bearer");

    } // usuarioCreateDTO

    // Método que permita refrescar token actual por uno nuevo

    @Transactional(readOnly = true)
    public TokenResponseDTO refreshToken(RefreshTokenDTO refreshTokenDTO) throws InvalidTokenException {
        
        // Obtener el email a partir del token 

        String email = jwtService.extractUsername(refreshTokenDTO.refreshToken());

        // Buscar el usuario a partir del token extraido 

        Usuario usuario =  usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado ningún usuario con email: " + email));

        // Validamos el token

        if(!jwtService.isTokenValid(refreshTokenDTO.refreshToken(), usuario)) {
            throw new InvalidTokenException("El refresh token no es un token válido.");

        } // if

        // Generar un nuevo access token para el usuario.

        String accessToken = jwtService.generateAccessToken(usuario);
        return new TokenResponseDTO(accessToken, refreshTokenDTO.refreshToken(), "Bearer");

    }

} // class