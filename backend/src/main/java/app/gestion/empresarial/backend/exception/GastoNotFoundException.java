package app.gestion.empresarial.backend.exception;

public class GastoNotFoundException extends RuntimeException {

    public GastoNotFoundException(String mensaje) {
        super(mensaje);
    }

}
