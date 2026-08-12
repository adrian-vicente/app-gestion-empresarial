package app.gestion.empresarial.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioAlreadyExistsException.class)
    public ResponseEntity<String> usuarioAlreadyExists(UsuarioAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ex.getMessage());

    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> usuarioNotFound(UsuarioNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> invalidToken(InvalidTokenException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
            
    }

    @ExceptionHandler(ProveedorNotFoundException.class)
    public ResponseEntity<String> proveedorNotFound(ProveedorNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(ProveedorNameAlreadyExistsException.class)
    public ResponseEntity<String> proveedorNameAlreadyExists(ProveedorNameAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ex.getMessage());
    }

} // class