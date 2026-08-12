package app.gestion.empresarial.backend.dto.Gasto;

import java.math.BigDecimal;

import app.gestion.empresarial.backend.model.enums.CategoriaGasto;
import app.gestion.empresarial.backend.model.enums.MetodoPago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GastoCreateDTO {

    // Declaración de atributos 

    private Long id;
    
    private CategoriaGasto categoriaGasto;
    private MetodoPago metodoPago;
    
    private String nombre;
    private String descripcion;

    @NotBlank(message = "El gasto tiene que tener un identificador o número de factura asociado.")
    @NotEmpty(message = "El gasto tiene que tener un identificador o número de factura asociado.")
    private String numeroFactura;

    private BigDecimal iva;
    private BigDecimal total;

} // class