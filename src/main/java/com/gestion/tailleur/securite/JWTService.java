package com.gestion.tailleur.securite;

import com.gestion.tailleur.Models.User;
import com.gestion.tailleur.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JWTService {
    private final String ENCRYPTION_KEY = "86970548be81bf98a8b5df27c8ce89635955ec04b6e2f103761ca3b0683f6a2b";
    private UserService userService;



    public Map<String, String> generate(String username){
        User user = this.userService.loadUserByUsername(username);
        return this.generateJWT(user);
    }

    private Map<String, String> generateJWT(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        long ExpirationTimeMillis = currentTimeMillis + 30 * 60 * 1000;

        final Map<String, Object> claims = Map.of(
                "nom", user.getNom(),
                Claims.EXPIRATION, new Date(ExpirationTimeMillis),
                Claims.SUBJECT, user.getEmail()
        );


        String token = Jwts.builder()
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(ExpirationTimeMillis))
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(getkey(), SignatureAlgorithm.HS256)
                .compact();
        return Map.of("token", token);
    }
    private Key getkey(){
        byte[] decode = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decode);
    }

    public String ExtractUser(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public  Boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

//    private Date getExpirationDateFromToken(String token) {
//        return this.getClaim(token, Claims::getExpiration);
//    }
    private<T> T getClaim(String token, Function<Claims, T> function){
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
