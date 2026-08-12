package app.gestion.empresarial.backend.dto.Login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
    
    @Email(message = "El email no tiene un formato válido.")
    @NotBlank(message = "El email es un campo obligatorio")
    String email, 

    @NotBlank(message = "La contraseña es un campo obligatorio")
    String password) {

} // class