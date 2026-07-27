package app.gestion.empresarial.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.gestion.empresarial.backend.dto.Usuario.UsuarioCreateDTO;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioDTO;
import app.gestion.empresarial.backend.exception.UsuarioException.UsuarioAlreadyExistsException;
import app.gestion.empresarial.backend.exception.UsuarioException.UsuarioNotFoundException;
import app.gestion.empresarial.backend.mapper.UsuarioMapper;
import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    // Inyección de dependencias

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    // Método para crear un nuevo usuario 

    @Transactional
    public UsuarioDTO createUsuario(UsuarioCreateDTO usuarioDTO) throws Exception {
        if(usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new UsuarioAlreadyExistsException("Ya existe un usuario con email: " + usuarioDTO.getEmail());

        } else usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));

        Usuario usuarioCreado = usuarioRepository.findByEmail(usuarioDTO.getEmail())
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado al usuario con email: " + usuarioDTO.getEmail()));

        return usuarioMapper.toDTO(usuarioCreado);

    }

} // class