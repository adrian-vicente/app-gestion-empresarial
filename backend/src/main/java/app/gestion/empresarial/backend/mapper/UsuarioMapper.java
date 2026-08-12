package app.gestion.empresarial.backend.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import app.gestion.empresarial.backend.config.ValidatorConfig;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioCreateDTO;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioDTO;
import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.repository.UsuarioRepository;

@Component
public class UsuarioMapper {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioMapper(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    // Método de conversión: DTO ==> ENTITY

    public Usuario toEntity(UsuarioCreateDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword( passwordEncoder.encode(usuarioDTO.getPassword()) );
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setTelefono(usuarioDTO.getTelefono());

        return usuario;

    }

    // Método de conversión: ENTITY ==> DTO

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        if(ValidatorConfig.identificadorValido(usuario.getId())) usuarioDTO.setId(usuario.getId());

        usuarioDTO.setNombre( usuario.getNombre() );
        usuarioDTO.setEdad( usuario.getEdad() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setRol(usuario.getRol().toString());
        usuarioDTO.setActivo(usuario.getActivo());
        usuarioDTO.setFechaCreacionUsuario(usuario.getFechaCreacionUsuario());
        usuarioDTO.setTelefono(usuario.getTelefono());

        return usuarioDTO;

    }

} // class