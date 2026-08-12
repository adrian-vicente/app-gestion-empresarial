package app.gestion.empresarial.backend.exception;

public class ProveedorNameAlreadyExistsException extends RuntimeException {

    public ProveedorNameAlreadyExistsException(String mensaje) {
        super(mensaje);
        
    }

}
