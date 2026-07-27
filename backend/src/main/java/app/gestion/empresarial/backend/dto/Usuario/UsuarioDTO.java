package app.gestion.empresarial.backend.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    // Declaración de atributos 

    private Long id;
    private String nombre;
    private String email;
    private Integer edad;

} // class