package app.gestion.empresarial.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Ingreso;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long>{

} // class