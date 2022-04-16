package com.mega.amps.jwtconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Function;

@Service
public class TokenProvider {

    private String secretKey = "secret_key";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction){
        final Claims claims = extractAll(token);
        return claimsFunction.apply(claims);
    }

    public Claims extractAll(String token){
        return Jwts.parser().setSigningKey(secretKey.getBytes(Charset.forName("UTF-8"))).parseClaimsJws(token.replace("{", "").replace("}","")).getBody();
    }

    public Boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    public String createToken(Map<String, Object> claims, String subject){
        String encodedString = Base64.getEncoder().encodeToString(secretKey.getBytes(Charset.forName("UTF-8")));

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, encodedString).compact();
    }

    public Boolean validToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isExpired(token));
    }

}
