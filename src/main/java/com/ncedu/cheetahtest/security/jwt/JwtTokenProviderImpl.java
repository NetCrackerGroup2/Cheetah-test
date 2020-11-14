package com.ncedu.cheetahtest.security.jwt;

import com.ncedu.cheetahtest.developer.entity.Developer;
import com.ncedu.cheetahtest.developer.entity.DeveloperDto;
import com.ncedu.cheetahtest.security.entity.AccessTokenDto;
import com.ncedu.cheetahtest.security.exceptions.JwtAuthenticationException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProviderImpl implements JwtTokenProvider{

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.exp}")
    private long expirationTime;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    @Override
    public String createToken(Developer developer) {
        Claims claims = Jwts.claims().setSubject(developer.getEmail());
        claims.put("role", developer.getRole());

        Date now = new Date();
        Date expiredMs = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredMs)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    @Override
    public String getEmail(String token) {
        return  Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("Token expired");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported jwt");
        } catch (MalformedJwtException e) {
            log.info("Malformed jwt");
        } catch (SignatureException e) {
            log.info("Invalid signature");
        } catch (Exception e) {
            log.info("invalid token");
        }
        return false;
    }
}