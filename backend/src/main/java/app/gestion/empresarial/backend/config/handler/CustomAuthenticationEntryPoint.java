package app.gestion.empresarial.backend.config.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import app.gestion.empresarial.backend.dto.Errores.ErrorResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Inyección de dependendencias 

    private final ObjectMapper objectMapper;
    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Implementación del método de la entidad. 

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException { 
        
        // Rellenar objeto con los datos del error generado
        
        ErrorResponseDTO error = new ErrorResponseDTO(LocalDateTime.now(), HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized", authException.getMessage(), request.getRequestURI());
        
        // Parámetros sobre la respuesta de la aplicación

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribir el JSON con toda la info del error 

        objectMapper.writeValue(response.getWriter(), error);

    } // commence

} // class