package app.gestion.empresarial.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.model.enums.Rol;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Comprobar si existe usuario con email determinado

    public boolean existsByEmail(String email);

    // Encontrar usuario a partir de email

    public Optional<Usuario> findByEmail(String email);

    // Encontrar usuario a partir del nombre

    public Optional<Usuario> findByNombre(String nombre);

    // Obtener todos los usuarios activos

    public List<Usuario> findByActivoTrue();

    // Obtener todos los usuarios a partir de un rol 

    public List<Usuario> findByRol(Rol rol);

} // class