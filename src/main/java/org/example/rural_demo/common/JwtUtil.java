package org.example.rural_demo.common;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    // 密钥，项目上线要换成更长的秘钥
    private static final SecretKey KEY = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes());
    // 过期时间 2小时
    private static final long EXPIRATION = 2 * 60 * 60 * 1000;

    /**
     * 生成token
     */
    public static String generateToken(Long userId, Integer role) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRATION);
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(KEY)
                .compact();
    }

    /**
     * 校验token是否有效
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户id
     */
    public static Long getUserId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
        return Long.valueOf(claims.getSubject());
    }

    /**
     * 获取角色
     */
    public static Integer getRole(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
        return claims.get("role", Integer.class);
    }
}