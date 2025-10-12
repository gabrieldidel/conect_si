package conectSI.conect_si.services;


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

    public String generateToken(String email, String nome) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", nome);
        return createToken(claims, email);
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
