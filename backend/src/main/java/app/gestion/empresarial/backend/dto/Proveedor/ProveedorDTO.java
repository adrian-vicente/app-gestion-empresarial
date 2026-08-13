package app.gestion.empresarial.backend.dto.Proveedor;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProveedorDTO {

    // Declaración de atributos 

    private Long id;
    private String nombre;
    private String descripcion;

    // Declaración de atributos para las relaciones

    private List<Long> gastosIds;

} // class