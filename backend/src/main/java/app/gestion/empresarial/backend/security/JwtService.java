package app.gestion.empresarial.backend.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.gestion.empresarial.backend.config.JwtConfig;

import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Inyección de dependencias

    private final JwtConfig jwtConfig;
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    // Método para obtener la clave de la firma

    private Key getSingingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Método para extraer todos los claims de un token

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith((javax.crypto.SecretKey) getSingingKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    // Método para extraer un claim de un token determinado

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    // Método para obtener el usuario de un token 

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    // Método para comprobar si el token ha expirado 

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
            .before(new Date());
    }

    // Método para generar un nuevo token 

    private String buildToken(Map<String, Object> claims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
            .claims(claims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSingingKey())
            .compact();

    }

    // Método para generar el token de acceso 

    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(
            new HashMap<>(),
            userDetails,
            jwtConfig.getExpiration()
        );
 
    }

    // Método para refrescar un token existente 

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(
            new HashMap<>(), userDetails, jwtConfig.getRefreshExpiration());
    }

    // Método para validar un token 

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

} // class