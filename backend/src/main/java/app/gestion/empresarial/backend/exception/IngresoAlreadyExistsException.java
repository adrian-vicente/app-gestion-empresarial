package app.gestion.empresarial.backend.exception;

public class IngresoAlreadyExistsException extends RuntimeException {

    public IngresoAlreadyExistsException(String mensaje) {
        super(mensaje);
        
    }

} // class