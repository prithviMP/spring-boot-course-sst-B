package com.example.vh.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //Generating JWT token

    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username) // subject --> unique identifier for a token generated for a specific user
                .claim("role", role) // extra information is passed as claims
                .setIssuedAt( new Date()) //
                .setExpiration( new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(getKey(), SignatureAlgorithm.HS256) // using the key to sign the auth token using given algorithm
                .compact();
    }

    // Extracting username

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract role
    public String extractRole(String token) {
        return  extractClaims(token).get("role").toString();
    }

    // to check token is expired
    public boolean isTokenExpired(String token) {
        return  extractClaims(token).getExpiration().before(new Date());
    }

    // validate token method

    public boolean validateToken(String token,String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
    }



}
