package com.xiaoxin.blog.common.utils;

import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static final long ACCESS_TOKEN_EXPIRATION = 60 * 60 * 1000L; // 1h
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000L; // 7d
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());
    public static String creatJwt(long userId,String username) {
        return Jwts.builder()
                .setSubject("USER_INFO")
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
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
        }catch (ExpiredJwtException e){
            throw new BlogException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            throw new BlogException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
    public static String creatRefreshToken(long userId,String username){
        String key="refresh_token_"+UUID.randomUUID();
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

    public static void main(String[] args) {
        System.out.println(creatJwt(1,"test"));
    }
}
