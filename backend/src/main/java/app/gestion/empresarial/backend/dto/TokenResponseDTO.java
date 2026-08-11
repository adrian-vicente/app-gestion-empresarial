package app.gestion.empresarial.backend.dto;

public record TokenResponseDTO(
    
    String accessToken, 
    String refreshToken,
    String tokenType) {
    
}