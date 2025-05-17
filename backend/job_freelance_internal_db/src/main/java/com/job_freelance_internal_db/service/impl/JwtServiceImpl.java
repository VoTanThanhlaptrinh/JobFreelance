package com.job_freelance_internal_db.service.impl;

import com.job_freelance_internal_db.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${application.service.impl.secret-key}")
    private String secretkey;
    @Value("${application.service.impl.expiration}")
    private long jwtExpiration;

    public String generateToken(UserDetails userDetails) {
        return gerenateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    @Override
    public boolean isTokenExpired(String token) {
        // TODO Auto-generated method stub
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        // TODO Auto-generated method stub
        return extractClaims(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        // TODO Auto-generated method stub
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    private String gerenateToken(HashMap<String, Object> claims, UserDetails userDetails) {
        // TODO Auto-generated method stub
        return buildToken(claims, userDetails);
    }

    private String buildToken(HashMap<String, Object> claims, UserDetails userDetails) {
        // TODO Auto-generated method stub
        return Jwts.builder().claims(claims).subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))// set thời điểm tạo ra
                // the token will be expired in 10 hours
                .expiration(new Date(System.currentTimeMillis() + Duration.ofSeconds(jwtExpiration*1000).toMillis())) // token sẽ hết hạn sau jwtExpiration
                .signWith(getSignInKey()).compact();

    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
