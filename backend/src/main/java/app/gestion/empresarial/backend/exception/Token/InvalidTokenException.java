package app.gestion.empresarial.backend.exception.Token;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String mensaje) {
        super(mensaje);
    }

} // class