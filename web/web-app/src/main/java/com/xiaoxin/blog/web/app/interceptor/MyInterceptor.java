package com.xiaoxin.blog.web.app.interceptor;

import com.xiaoxin.blog.common.login.LoginUser;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterceptor implements HandlerInterceptor{
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截到了：" + request.getRequestURI());
        String jwt = request.getHeader("auth-jwt");
        Claims claims = JwtUtil.parseJwt(jwt);
        LoginUserHolder.set(new LoginUser(claims.get("userId", Long.class), claims.get("username", String.class)));
        if (redisTemplate.hasKey("token-blacklist-" + jwt)) {
            response.setStatus(401);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.remove();
    }
}