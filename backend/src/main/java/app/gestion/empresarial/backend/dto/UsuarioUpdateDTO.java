package app.gestion.empresarial.backend.dto;

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
public class UsuarioUpdateDTO {

    private Long id;

    // Longitud mínimo 3 y máximo de 20 a 50 carácteres
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 carácteres")
    private String nombre;

    // Longitud máxima de 254 carácteres (Estándar RFC)
    @Email(message = "El email no tiene el formato correcto")
    @Size(max = 254, message = "El email debe tener 254 cáracteres cómo máximo")
    private String email;

    // Longitud mínima de 8 carácteres entre 64 y 128 carácteres
    @Size(min = 64, max = 128, message = "La password debe tener entre 64 y 128 carácteres")
    private String password;

    // Edad de rango máximo de 60 años
    @Min(value = 18, message = "La edad debe tener mínimo 18 años.")
    @Max(value = 60, message = "La edad debe tener cómo máximo 60 años.")
    private Integer edad;

} // clas