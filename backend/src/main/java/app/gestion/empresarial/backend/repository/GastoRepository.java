package app.gestion.empresarial.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.gestion.empresarial.backend.model.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    // Obtener los gastos a partir del id de un proveedor 

    public List<Gasto> findByProveedorId(Long id);

    // Comprobar si existe un gasto por número de factura

    public boolean existsByNumeroFactura(String numeroFactura);

    // Comprobar si un gasto existe por id

    public boolean existsById(Long id);

    // Obtener todos los gastos a partir del id de un usuario 

    public List<Gasto> findByUsuarioId(Long id);

}