package app.gestion.empresarial.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    // Obtener los gastos a partir del id de un proveedor 

    List<Gasto> findByProveedorId(Long id);

}