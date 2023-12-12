package com.skillprint.skillprintbackend.Security.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    //Generate access token
    public String generateAccessToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    //Validate access token
    public boolean isTokenValid(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;

        }catch (Exception e){

            log.error("Token invalid, error: ".concat(e.getMessage()));

        return false;
        }
    }

    //Get the token username
    public String getUsernameFromToken(String token){

        return getClaim(token, Claims::getSubject);
    }

    //Get a single claim
    public <T> T getClaim(String token, Function<Claims, T> claimTFunction){

        Claims claims = extractAllClaims(token);
        return claimTFunction.apply(claims);

    }


    //Obtain all token claims (information about who generated the token data)
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Get token signature
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
