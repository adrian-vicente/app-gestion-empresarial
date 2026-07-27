package app.gestion.empresarial.backend.exception.UsuarioException;

public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

} // class