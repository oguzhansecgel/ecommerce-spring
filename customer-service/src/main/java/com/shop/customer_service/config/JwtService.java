package com.shop.customer_service.config;

import com.shop.customer_service.entity.Role;
import com.shop.customer_service.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "c69932776957fb3a2c54609519a4b8ff7d6fca8060ee2bbe9b1fe4b920f1d737";
    private static final long ACCESS_EXPIRATION_TIME = 5 * 60 * 1000; // 5 dakika
    private static final long REFRESH_TOKEN_EXPIRATION = 45 * 60 * 1000; // 45 dakika


    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> claims, UserDetails userDetails)
    {
        int customerId = ((User) userDetails).getId();
        Role role = ((User) userDetails).getRole();
        String name = ((User) userDetails).getUsername();
        String surname = ((User) userDetails).getUsername();
        claims.put("customerId", customerId);
        claims.put("role", role);
        claims.put("name", name);
        claims.put("surname", surname);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ACCESS_EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(UserDetails userDetails)
    {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }
    public String generateRefreshToken(Map<String, Object> claims, UserDetails userDetails)
    {
        int customerId = ((User) userDetails).getId();
        Role role = ((User) userDetails).getRole();
        String name = ((User) userDetails).getUsername();
        String surname = ((User) userDetails).getUsername();
        claims.put("customerId", customerId);
        claims.put("role", role);
        claims.put("name", name);
        claims.put("surname", surname);
        System.out.println("Refresh Token Expiration Time: " + (System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXPIRATION))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public int extractUserIdFromToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (Integer) claims.get("customerId");
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
    public boolean isTokenValid(String token,UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public boolean isRefreshTokenValid(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}