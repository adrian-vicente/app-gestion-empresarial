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
public class ProveedorCreateDTO {

    // Declaración de atributos 

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String nombre;
    
    private String descripcion;

} // class