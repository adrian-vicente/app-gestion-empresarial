package app.gestion.empresarial.backend.service;

import app.gestion.empresarial.backend.mapper.UsuarioMapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.gestion.empresarial.backend.dto.Usuario.UsuarioDTO;
import app.gestion.empresarial.backend.exception.UsuarioException.UsuarioNotFoundException;
import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    // Inyección de dependencias 

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Método para obtener listado de todos los usuarios

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTOs = new ArrayList<UsuarioDTO>();
        for(Usuario u : usuarios) { usuariosDTOs.add( usuarioMapper.toDTO(u) ); } // for
        return usuariosDTOs;

    }

    // Método para obtener usuario a partir de un identificador 

    @Transactional(readOnly = true)
    public UsuarioDTO findUserById(Long id) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado ningún usuario con id: " + id));

        return usuarioMapper.toDTO(usuario);

    } // findUserById

    // Método para obtener el usuario autenticado 

    // Método para buscar el usuario por nombre 

    // Método para filtrar usuarios por el estado 

    // Método para filtrar usuarios por rol

    // Método para actualizar un usuario 

    // Método para actualizar el perfil de usuario autenticado 

    // Método para cambiar la password de usuario autenticado

    // Método para activar un usuario 

    // Método para desactivar un usuario

    // Método para eliminar un usuario de la aplicación 

} // class