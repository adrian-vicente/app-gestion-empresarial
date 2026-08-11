package app.gestion.empresarial.backend.exception;

public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

} // class