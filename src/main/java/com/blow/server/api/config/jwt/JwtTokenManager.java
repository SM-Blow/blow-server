package com.blow.server.api.config.jwt;

import com.blow.server.api.common.exception.TokenException;
import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.config.AuthConfig;
import com.blow.server.api.service.user.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtTokenManager {

    private final AuthConfig authConfig;
    private final ZoneId KST = ZoneId.of("Asia/Seoul");
    private final UserDetailsServiceImpl userDetailsService;

    public String createAccessToken(Long userId) {
        val signatureAlgorithm = SignatureAlgorithm.HS256;
        val secretKeyBytes = DatatypeConverter.parseBase64Binary(authConfig.getJwtSecretKey());
        val signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        val exp = ZonedDateTime.now(KST).toLocalDateTime().plusHours(6).atZone(KST).toInstant();

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setExpiration(Date.from(exp))
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }

    public String createRefreshToken(Long userId) {
        val signatureAlgorithm = SignatureAlgorithm.HS256;
        val secretKeyBytes = DatatypeConverter.parseBase64Binary(authConfig.getJwtSecretKey());
        val signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        val exp = ZonedDateTime.now(KST).toLocalDateTime().plusDays(14).atZone(KST).toInstant();

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setExpiration(Date.from(exp))
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }


    public boolean verifyAuthToken (String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (SignatureException | ExpiredJwtException e) {
            throw new TokenException(ExceptionMessage.INVALID_TOKEN.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public String getUserIdFromAuthToken (String token) {
        try {
            val claims = getClaimsFromToken(token);

            return claims.getSubject();
        } catch (SignatureException | ExpiredJwtException e) {
            throw new TokenException(ExceptionMessage.INVALID_TOKEN.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        val userId = getUserIdFromAuthToken(token);

        val userDetails = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, null);
    }


    private Claims getClaimsFromToken (String token) throws SignatureException {
        return Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(authConfig.getJwtSecretKey()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
