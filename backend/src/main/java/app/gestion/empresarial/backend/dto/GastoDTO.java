package app.gestion.empresarial.backend.dto;

import java.math.BigDecimal;

import app.gestion.empresarial.backend.model.enums.CategoriaGasto;
import app.gestion.empresarial.backend.model.enums.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GastoDTO {

    // Declaración de atributos 

    private Long id;
    private CategoriaGasto categoriaGasto;
    private MetodoPago metodoPago;
    private BigDecimal iva;
    private BigDecimal total;
    private String nombre;
    private String descripcion;
    private String numeroFactura;

} // class