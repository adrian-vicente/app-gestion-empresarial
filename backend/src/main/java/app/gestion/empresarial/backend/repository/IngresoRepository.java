package app.gestion.empresarial.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long>{

    // Comprobar si existe ingreso mediante el nombre 

    public boolean existsByNombre(String nombre);

} // class