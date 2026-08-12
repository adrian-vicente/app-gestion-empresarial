package app.gestion.empresarial.backend.dto.Login;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDTO(
    
    @NotBlank(message = "El refresh token debe tener valor. No debe estar vacío.")
    String refreshToken
    
) {}
