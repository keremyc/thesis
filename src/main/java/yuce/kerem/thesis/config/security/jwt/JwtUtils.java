package yuce.kerem.thesis.config.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Kerem(Nurullah)
 * @version 1.0
 * @date 6/21/21 7:33 PM
 */

@PropertySource(value = "classpath:jwt.properties")
@Component
public class JwtUtils {

    @Value("${jwt.app.secret}")
    private String jwtSecret;

    @Value("${jwt.app.expirationInMillis}")
    private Long jwtExpirationInMillis;

    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationInMillis))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUserFromJwtToken(String token) {
        if (token == null) {
            return null;
        }

        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException exp) {
            exp.printStackTrace();
        } catch (MalformedJwtException exp){
            exp.printStackTrace();
        } catch (ExpiredJwtException exp) {
            exp.printStackTrace();
        } catch (UnsupportedJwtException exp) {
            exp.printStackTrace();
        } catch (IllegalArgumentException exp) {
            exp.printStackTrace();
        }

        return false;
    }

}
