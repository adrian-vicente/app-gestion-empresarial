package app.gestion.empresarial.backend.dto.Proveedor;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProveedorUpdateDTO {

    // Declaración de atributos 

    private Long id;

    @NotBlank(message = "El nombre del proveedor es obligatori")
    private String nombre;
    
    private String descripcion;

} // class