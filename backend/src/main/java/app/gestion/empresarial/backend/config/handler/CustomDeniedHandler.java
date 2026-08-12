package app.gestion.empresarial.backend.config.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import app.gestion.empresarial.backend.dto.Login.ErrorResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class CustomDeniedHandler implements AccessDeniedHandler {

    // Inyección de dependencias 

    private final ObjectMapper objectMapper;
    public CustomDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

    }

    // Implementación del método de la interfaz

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        // Rellenar objeto con los datos del error generado y capturado 

        ErrorResponseDTO error = new ErrorResponseDTO(LocalDateTime.now(), HttpServletResponse.SC_FORBIDDEN, "Forbidden", "No dispone de permisos para acceder a este recurso", request.getRequestURI());

        // Parámetros sobre la respuesta que dará la aplicación 

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Escribir el JSON con toda la información del error generado 

        objectMapper.writeValue(response.getWriter(), error);

    } // handle

} // class