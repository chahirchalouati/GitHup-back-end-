/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Service;

import static Coders.Constants.SecurityConstants.EXPIRATION_TIME;
import static Coders.Constants.SecurityConstants.SECRET;

import Coders.Service.MyUserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Component
@Slf4j
public class JwtUtils {

    public String generateJwtToken(Authentication authentication) {

        MyUserDetailsImpl userPrincipal = (MyUserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken, HttpServletRequest httpServletRequest) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.info("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            log.warn("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Expired JWT token");
            httpServletRequest.setAttribute("expired", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported JWT exception");
        } catch (IllegalArgumentException ex) {
            log.info("Jwt claims string is empty");
        }
        return false;

    }
}
