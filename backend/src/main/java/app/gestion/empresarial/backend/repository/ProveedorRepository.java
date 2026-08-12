package app.gestion.empresarial.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{

    // Comprobar que no exista un nombre de proveedor duplicado 

    public boolean existsByNombre(String nombre);

} // class