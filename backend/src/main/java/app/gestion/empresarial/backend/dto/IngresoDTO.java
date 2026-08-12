package app.gestion.empresarial.backend.dto;

import java.math.BigDecimal;

import app.gestion.empresarial.backend.model.enums.CategoriaIngreso;
import app.gestion.empresarial.backend.model.enums.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IngresoDTO {

    // Declaración de atributos 

    private Long id;
    private MetodoPago metodoPago;
    private CategoriaIngreso categoriaIngreso;
    private String nombre;
    private String descripcion;
    private BigDecimal iva;
    private BigDecimal total;

    // Declaración de atributos para las relaciones 

    private Long usuarioId;

} // class