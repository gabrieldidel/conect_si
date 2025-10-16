package conectSI.conect_si.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private static final String SECRET_KEY = "c4b8d0f0-8c5e-4e41-9c8a-40e3d4f3a1b7";

    public String generateToken(Integer id, String email, String nome) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", nome);
        claims.put("id", id);
        return createToken(claims, email);
    }

    private static Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    public static String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public static String extractNome(String token) {
        return extractAllClaims(token).get("nome", String.class);
    }

    public static Long extractId(String token) {
        Object id = extractAllClaims(token).get("id");
        return id != null ? Long.parseLong(id.toString()) : null;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long expirationTime = 1000 * 60 * 60; // 1 hora
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
