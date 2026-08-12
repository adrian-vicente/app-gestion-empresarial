package app.gestion.empresarial.backend.dto.Usuario;

import java.time.LocalDate;

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
    private String rol;
    private Boolean activo;
    private LocalDate fechaCreacionUsuario;
    private LocalDate fechaUltimoLogin;
    private LocalDate fechaUltimaActualizacion;
    private String telefono;

} // class