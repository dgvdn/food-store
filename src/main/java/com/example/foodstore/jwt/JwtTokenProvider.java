package com.example.foodstore.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    // generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpiration);

        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(key()).compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //get username from token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }

    //validate token
    public boolean validateToken(String token) {
       try{
              Jwts.parserBuilder()
                     .setSigningKey(key())
                     .build()
                     .parse(token);
              return true;
       }catch (MalformedJwtException e){
           LOGGER.error("Invalid JWT token");
       }catch (IllegalArgumentException e){
           LOGGER.error("JWT claims string is empty");
       } catch (Throwable e){
           LOGGER.error("JWT token is expired or invalid");
       }

    return false;}

}
