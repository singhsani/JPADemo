package com.jpaimplementation.jpaDemo.JwtConfiguration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    String secreatKey="fsdfsdfsdfsdfsdfsdfsdfsdfsdfssdfsdfsdfsderterterthdfdfgertertdssdaffsdhfcbvxc";

    public String genrateToken(String userName){
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SignatureAlgorithm.HS256,secreatKey)
                .compact();
    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secreatKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
