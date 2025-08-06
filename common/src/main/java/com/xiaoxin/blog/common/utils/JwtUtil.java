package com.xiaoxin.blog.common.utils;

import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static long tokenExpiration = 60 * 60 * 1000L;
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());
    public static String creatJwt(long userId,String username) {
        return Jwts.builder()
                .setSubject("USER_INFO")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
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
}
