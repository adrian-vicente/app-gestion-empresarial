package app.gestion.empresarial.backend.dto.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioCreateDTO {

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 carácteres")
    private String password;

    // Longitud mínimo 3 y máximo de 20 a 50 carácteres
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 carácteres")
    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    // Longitud máxima de 254 carácteres (Estándar RFC)
    @Email(message = "El email no tiene el formato correcto")
    @Size(max = 254, message = "El email debe tener 254 carácteres cómo máximo")
    @NotBlank(message = "El email es un campo obligatorio")
    private String email;

    // Edad de rango máximo de 60 años
    @Min(value = 18, message = "La edad debe tener mínimo 18 años.")
    @Max(value = 60, message = "La edad debe tener cómo máximo 60 años.")
    private Integer edad;

    private String telefono;

} // class