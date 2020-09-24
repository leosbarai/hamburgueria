package com.github.leosbarai.hamburgueria.user.security;

import com.github.leosbarai.hamburgueria.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwk-key}")
    private String key;

    public String tokenGeneration(User user) {
        LocalDateTime dateTimeExp = LocalDateTime.now().plusMinutes(Long.valueOf(expiration));
        Date date = Date.from(dateTimeExp.atZone(ZoneId.systemDefault()).toInstant());

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("User Id: ", user.getId());
        claims.put("User name: ", user.getName());

        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setExpiration(date)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValidate(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime dateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(dateTime);
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginUser(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }
}
