package app.gestion.empresarial.backend.dto.Usuario;

import java.time.LocalDate;

import app.gestion.empresarial.backend.model.enums.Rol;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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

    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 carácteres")
    private String nombre;

    @Email(message = "El email no tiene el formato correcto")
    @Size(max = 254, message = "El email debe tener 254 cáracteres cómo máximo")
    @Column(nullable = false, unique = true)
    private String email;

    @Min(value = 18, message = "La edad debe tener mínimo 18 años.")
    @Max(value = 60, message = "La edad debe tener cómo máximo 60 años.")
    private Integer edad;
    
    private Rol rol;
    private Boolean activo;
    private LocalDate fechaCreacionUsuario;
    private LocalDate fechaUltimoLogin;
    private LocalDate fechaUltimaActualizacion;
    private String telefono;

} // class