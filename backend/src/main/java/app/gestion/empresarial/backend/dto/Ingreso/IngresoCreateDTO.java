package app.gestion.empresarial.backend.dto.Ingreso;

import java.math.BigDecimal;

import app.gestion.empresarial.backend.model.enums.CategoriaIngreso;
import app.gestion.empresarial.backend.model.enums.MetodoPago;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IngresoCreateDTO {

    // Declaración de atributos 

    private MetodoPago metodoPago;
    private CategoriaIngreso categoriaIngreso;

    @NotBlank(message = "El ingreso debe tener un nombre asociado")
    private String nombre;
    private String descripcion;
    private BigDecimal iva;
    private BigDecimal total;

} // class