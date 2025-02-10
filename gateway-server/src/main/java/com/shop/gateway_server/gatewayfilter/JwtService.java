package com.shop.gateway_server.gatewayfilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtService {
    public static final String SECRET_KEY = "c69932776957fb3a2c54609519a4b8ff7d6fca8060ee2bbe9b1fe4b920f1d737";

    private final Key signingKey;

    public JwtService() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        String role = claims.get("role", String.class);  // Tekil "role" anahtarını çekiyoruz
        System.out.println("********************* ROLE : " + role);

        return role != null ? Collections.singletonList(role) : Collections.emptyList();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)  // Burada getSignInKey() yerine signingKey kullanıyoruz
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public void validateToken(String token) {
        try {
            System.out.println("***************************** token validate edildi");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("************* Claims: " + claims);
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("**************************** token valid edilemedi");
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
