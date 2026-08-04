package app.gestion.empresarial.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Comprobar si existe usuario con email determinado

    public boolean existsByEmail(String email);

    // Encontrar usuario a partir de email

    public Optional<Usuario> findByEmail(String email);

    // Encontrar usuario a partir del nombre

    public Optional<Usuario> findByNombre(String nombre);

} // class