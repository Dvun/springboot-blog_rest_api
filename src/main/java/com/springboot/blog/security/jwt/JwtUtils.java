package com.springboot.blog.security.jwt;

import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.security.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-date}")
    private int jwtExpirationDate;


    public Boolean validateJwtToken(String token) {
        try {
             Jwts.parserBuilder()
                    .setSigningKey(key())
                     .requireIssuer("springboot_rest_api")
                    .build()
                     .parse(token);
             return true;
        } catch (SignatureException ex) {
            throw new BlogApiException("Invalid Jwt signature");
        } catch (MalformedJwtException ex) {
            throw new BlogApiException("Invalid Jwt token");
        } catch (ExpiredJwtException ex) {
            throw new BlogApiException("Expired Jwt token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogApiException("Unsupported Jwt token");
        } catch (IllegalArgumentException ex) {
            throw new BlogApiException("Jwt claims string is empty");
        }
    }

    public String generateJwtToken(Authentication authentication) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .claim("username", principal.getUsername())
                .claim("email", principal.getEmail())
                .setIssuer("springboot_rest_api")
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    public String getUsernameFromJWT(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody().get("email").toString();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}
