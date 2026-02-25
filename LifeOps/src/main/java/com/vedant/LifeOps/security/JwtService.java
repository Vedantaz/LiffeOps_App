package com.vedant.LifeOps.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());

    }

    // 🔥 Generate token with roles

    public String generateToken(String username, String role){

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60*60))   // 1 hour
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();

    }
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean validateToken(String token, String username){

        return extractUsername(token).equals(username)
                && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token){

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
