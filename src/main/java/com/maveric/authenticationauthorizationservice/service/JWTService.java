package com.maveric.authenticationauthorizationservice.service;

import com.maveric.authenticationauthorizationservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private String SECRET_KEY = "secret";

    public String getUserId(String token){
        return getClaim(token,Claims::getSubject);
    }

    public Date getExpiration(String token){
        return getClaim(token,Claims::getExpiration);
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims =extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

    public String generateToken(User user){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,user.getId());
    }

    private String createToken(Map<String,Object> claims , String Id){
        return Jwts.builder().setClaims(claims).setSubject(Id).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }

    public Boolean validateToken(String token , User user){
        final String id =getUserId(token);
        return (id.equals(user.getId()) && !isTokenExpired(token));
    }
}
