package com.xiaoxin.blog.common.utils;

import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static final long ACCESS_TOKEN_EXPIRATION = 60 * 60 * 1000L; // 1h
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000L; // 7d
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());

    public static String creatJwt(long userId, String username) {
        return Jwts.builder()
                   .setSubject("USER_INFO")
                   .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                   .claim("username", username)
                   .claim("userId", userId)
                   .signWith(tokenSignKey)
                   .compact();
    }

    // 新增：支持自定义过期时间的Token生成
    public static String creatJwtWithExpiration(long userId, String username, long expirationSeconds) {
        return Jwts.builder()
                   .setSubject("USER_INFO")
                   .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000L))
                   .claim("username", username)
                   .claim("userId", userId)
                   .signWith(tokenSignKey)
                   .compact();
    }

    public static Claims parseJwt(String jwt) {
        if (jwt == null) throw new BlogException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                                        .setSigningKey(tokenSignKey)
                                        .build()
                                        .parseClaimsJws(jwt);
            Claims body = claimsJws.getBody();
            return body;
        } catch (ExpiredJwtException e) {
            throw new BlogException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new BlogException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    public static String creatRefreshToken(long userId, String username) {
        String key = "refresh_token_" + UUID.randomUUID();
        return Jwts.builder()
                   .setSubject("REFRESH_TOKEN")
                   .claim("userId", userId)
                   .claim("username", username)
                   .claim("key", key)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                   .signWith(tokenSignKey)
                   .compact();
    }

    public static String getRefreshTokenKey(String refreshToken) {
        return parseJwt(refreshToken).get("key", String.class);
    }

    /**
     * 获取访问令牌的默认过期时间（秒）
     */
    public static Long getDefaultExpiration() {
        return ACCESS_TOKEN_EXPIRATION / 1000L; // 转换为秒：3600秒 = 1小时
    }

    /**
     * 获取刷新令牌的过期时间（秒）
     */
    public static Long getRefreshTokenExpiration() {
        return REFRESH_TOKEN_EXPIRATION / 1000L; // 转换为秒：604800秒 = 7天
    }

    /**
     * 获取指定Token的剩余过期时间（秒）
     */
    public static Long getTokenExpiration(String token) {
        try {
            Claims claims = parseJwt(token);
            Date expiration = claims.getExpiration();
            Date now = new Date();

            if (expiration.before(now)) {
                return 0L; // 已过期
            }

            return (expiration.getTime() - now.getTime()) / 1000L; // 转换为秒
        } catch (Exception e) {
            return 0L; // 解析失败认为已过期
        }
    }

    /**
     * 从Token中获取用户ID
     */
    public static Long getUserIdFromToken(String token) {
        Claims claims = parseJwt(token);
        return claims.get("userId", Long.class);
    }

    /**
     * 从Token中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = parseJwt(token);
        return claims.get("username", String.class);
    }

    /**
     * 验证Token是否有效（未过期且能正常解析）
     */
    public static boolean isTokenValid(String token) {
        try {
            Claims claims = parseJwt(token);
            return claims.getExpiration().after(new Date()); // 检查是否过期
        } catch (Exception e) {
            return false;
        }
    }

    // 兼容原方法名
    public static boolean validateToken(String token) {
        return isTokenValid(token);
    }

    public static void main(String[] args) {
        System.out.println("Access Token: " + creatJwt(1, "test"));
        System.out.println("Default Expiration: " + getDefaultExpiration() + " seconds");
        System.out.println("Refresh Token Expiration: " + getRefreshTokenExpiration() + " seconds");
    }
}
