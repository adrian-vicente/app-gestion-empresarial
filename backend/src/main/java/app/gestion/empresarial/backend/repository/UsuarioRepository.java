package app.gestion.empresarial.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Comprobar que no exista usuario con email determinado

    public boolean existsByEmail(String email);

    // Obtener usuario a partir del identificador 

    public Optional<Usuario> findByEmail(String email);

} // class