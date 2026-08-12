package app.gestion.empresarial.backend.model;

import java.math.BigDecimal;

import app.gestion.empresarial.backend.model.enums.CategoriaGasto;
import app.gestion.empresarial.backend.model.enums.MetodoPago;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gastos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gasto {

    // Declaración de atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaGasto categoriaGasto;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    private BigDecimal iva = new BigDecimal(21.00);
    private BigDecimal total;

    private String nombre;
    private String descripcion;
    private String numeroFactura;

    // Declaración de atributos para las relaciones 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

} // class