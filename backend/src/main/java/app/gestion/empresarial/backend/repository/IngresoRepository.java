package app.gestion.empresarial.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long>{

    // Comprobar si existe ingreso mediante el nombre 

    public boolean existsByNombre(String nombre);

    // Obtener listado de ingresos a partir del id de usuario 

    public List<Ingreso> findByUsuarioId(Long id);

} // class