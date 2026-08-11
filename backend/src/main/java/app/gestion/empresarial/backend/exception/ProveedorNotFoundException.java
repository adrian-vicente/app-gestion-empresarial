package app.gestion.empresarial.backend.exception;

public class ProveedorNotFoundException extends RuntimeException {

    public ProveedorNotFoundException(String mensaje) {
        super(mensaje);
    }

} // class