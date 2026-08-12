package app.gestion.empresarial.backend.exception;

public class GastoAlreadyExistsException extends RuntimeException {

    public GastoAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

} // class