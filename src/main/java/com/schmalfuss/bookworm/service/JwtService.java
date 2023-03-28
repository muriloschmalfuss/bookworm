package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Boolean validateToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().isSigned(token);
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(UserDTO user) {

        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setIssuer(user.getName())
                .setIssuedAt(new Date())
                .signWith(secretKey)
                .compact();
    }
}
