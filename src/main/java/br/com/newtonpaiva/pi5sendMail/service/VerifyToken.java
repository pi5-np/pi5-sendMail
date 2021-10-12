package br.com.newtonpaiva.pi5sendMail.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class VerifyToken {

    private final String jwtSecret = "MySecret";

    public String findToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        String token = bearer.substring(7, bearer.length());
        return token;
    }

    public void validate(String bearer) throws Exception {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(bearer);
        } catch (SignatureException ex) {
            throw new SignatureException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new Exception("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new UnsupportedJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("JWT claims string is empty.");
        }
    }

}
