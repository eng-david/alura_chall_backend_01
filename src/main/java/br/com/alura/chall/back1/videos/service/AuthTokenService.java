package br.com.alura.chall.back1.videos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.chall.back1.videos.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthTokenService {

    @Value("${forum.jwt.expiration}")
    private String expirationDelay;
    
    @Value("${forum.jwt.secret}")
    private String secret;
    
    public String generateToken(Authentication authentication) {
    
        User loggedUser = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expiration = new Date(today.getTime() + Long.parseLong(expirationDelay) );

        return Jwts.builder()
                .setIssuer("videos app")
                .setSubject(loggedUser.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    } 

    public Long getUserId(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject() );
    }
}
