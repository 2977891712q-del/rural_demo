package org.example.rural_demo.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    //密钥，长度≥32字符
    private static final String SECRET_KEY_STR = "ruralDemo2026SecretKeyForJwtToken12345678";
    //token有效期 2小时
    private static final long EXPIRATION_TIME = 2 * 60 * 60 * 1000L;

    private static SecretKey getSecretKey() {
        byte[] keyBytes = SECRET_KEY_STR.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成token，存入userId、role
     */
    public static String generateToken(Long userId, Integer role) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSecretKey())
                .compact();
    }

    /**
     * 解析token，获取载荷信息
     */
    private static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("userId", Long.class);
    }

    public static Integer getRole(String token) {
        Claims claims = getClaims(token);
        return claims.get("role", Integer.class);
    }

    /**
     * 校验token是否合法、未过期
     */
    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            //token过期
        } catch (JwtException e) {
            //非法token
        }
        return false;
    }
}