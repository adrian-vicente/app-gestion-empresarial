package app.gestion.empresarial.backend.config;

public class ValidatorConfig {

    // Método para validar el identificador de una clase 

    public static boolean identificadorValido(Long id) {
        return id > 0;
    }

} // class