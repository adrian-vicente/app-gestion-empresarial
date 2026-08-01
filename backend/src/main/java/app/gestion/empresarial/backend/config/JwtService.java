package app.gestion.empresarial.backend.config;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Inyección de dependencias

    private final JwtConfig jwtConfig;
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    // Obtener la clave de la firma 

    private Key getSingingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

} // class