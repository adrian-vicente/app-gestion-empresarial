package app.gestion.empresarial.backend.service;

import app.gestion.empresarial.backend.mapper.UsuarioMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.gestion.empresarial.backend.dto.Usuario.UsuarioDTO;
import app.gestion.empresarial.backend.dto.Usuario.UsuarioUpdateDTO;
import app.gestion.empresarial.backend.exception.UsuarioNotFoundException;
import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.model.enums.Rol;
import app.gestion.empresarial.backend.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @Transactional(readOnly = true)
    public UsuarioDTO getAuthenticatedUser() throws UsuarioNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            throw new UsuarioNotFoundException("No se ha encontrado a ningún usuario autenticado.");
        
        } // if

        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findByEmail(usuarioAutenticado.getEmail())
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado a ningún usuario con email: " + usuarioAutenticado.getEmail()));

        return usuarioMapper.toDTO(usuario);

    } // getAuthenticatedUser

    // Método para buscar el usuario por nombre 

    @Transactional(readOnly = true)
    public UsuarioDTO findUserByNombre(String nombre) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findByNombre(nombre)
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado a ningún usuario con nombre: " + nombre));

        return usuarioMapper.toDTO(usuario);

    } // findUserByNombre

    // Método para obtener los usuarios activos

    @Transactional(readOnly = true)
    public List<UsuarioDTO> getActiveUsers() {
        List<Usuario> usuariosActivos = usuarioRepository.findByActivoTrue();
        List<UsuarioDTO> usuariosActivosDTO = new ArrayList<UsuarioDTO>();
        for(Usuario u : usuariosActivos) usuariosActivosDTO.add(usuarioMapper.toDTO(u));
        return usuariosActivosDTO;

    }

    // Método para filtrar usuarios por rol

    @Transactional(readOnly = true)
    public List<UsuarioDTO> getUsersByRol(String rol) {
        List<Usuario> usuarios = usuarioRepository.findByRol( Rol.valueOf( rol.toUpperCase() ) );
        List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();
        for(Usuario u : usuarios) usuarioDTOs.add(usuarioMapper.toDTO(u));
        return usuarioDTOs;

    } // getUsersByRol

    // Método para actualizar el perfil de un usuario autenticado.

    @Transactional
    public UsuarioDTO updateUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()) {
            throw new UsuarioNotFoundException("No se ha encontrado a ningún usuario autenticado.");

        } // if

        Usuario usuarioActual = (Usuario) auth.getPrincipal();
            usuarioActual.setNombre(usuarioUpdateDTO.getNombre());
            usuarioActual.setEmail(usuarioUpdateDTO.getEmail());
            usuarioActual.setPassword(usuarioUpdateDTO.getPassword());
            usuarioActual.setEdad(usuarioUpdateDTO.getEdad());

        Usuario usuarioModifcado = usuarioRepository.saveAndFlush(usuarioActual);
        return usuarioMapper.toDTO(usuarioModifcado);

    } // updateUsuario

    // Método para activar un usuario 

    // Método para desactivar un usuario

    // Método para eliminar un usuario de la aplicación 

} // class