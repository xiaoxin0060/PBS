package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.login.LoginUser;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.common.utils.CodeUtil;
import com.xiaoxin.blog.common.utils.JwtUtil;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.admin.mapper.UserMapper;
import com.xiaoxin.blog.web.admin.service.AuthService;
import com.xiaoxin.blog.web.admin.vo.AuthVo;
import com.xiaoxin.blog.web.admin.vo.CaptchaVo;
import com.xiaoxin.blog.web.admin.vo.UserRegistVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl extends ServiceImpl<UserMapper, User>
        implements AuthService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        String Code = specCaptcha.text().toLowerCase();
        String key = "auth-captcha"+ UUID.randomUUID();
        String url= specCaptcha.toBase64();
        redisTemplate.opsForValue().set(key, Code,10, TimeUnit.MINUTES);
        return new CaptchaVo(url, key);
    }

    @Override
    public Map<String, String> login(AuthVo authVo) {
        if(authVo.getCaptchaCode()== null) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        String code = redisTemplate.opsForValue().get(authVo.getCaptchaKey());
        if(code==null) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        if(!code.equals(authVo.getCaptchaCode())) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, authVo.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(user==null) throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        if (user.getStatus()==1) throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        if(!user.getPassword().equals(authVo.getPassword())) throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);

        Map<String, String> tokenMap = new HashMap<>();
        String refreshToken = JwtUtil.creatRefreshToken(user.getId(), user.getUsername());
        String token = JwtUtil.creatJwt(user.getId(), user.getUsername());
        tokenMap.put("refreshToken",refreshToken );
        tokenMap.put("token", token);
        String key = JwtUtil.getRefreshTokenKey(refreshToken);
        redisTemplate.opsForValue().set(key,user.getId().toString(),7,TimeUnit.DAYS);
        return tokenMap;
    }

    @Override
    public void register(UserRegistVo userRegistVo) {
        User user = new User();
        BeanUtils.copyProperties(userRegistVo,user);
        user.setRole(1);
        user.setStatus(0);
        userMapper.insert(user);
    }

    @Override
    public Map<String, String> refreshToken(String refreshToken) {
        String key = JwtUtil.getRefreshTokenKey(refreshToken);
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) throw new BlogException(ResultCodeEnum.TOKEN_INVALID);
        Claims claims = JwtUtil.parseJwt(refreshToken);
        String username = claims.get("username", String.class);
        Long userId = claims.get("userId", Long.class);
        redisTemplate.delete(key);

        Map<String, String> tokenMap = new HashMap<>();
        refreshToken = JwtUtil.creatRefreshToken(userId,username);
        String token = JwtUtil.creatJwt(userId,username);
        tokenMap.put("refreshToken",refreshToken );
        tokenMap.put("token", token);
        key = JwtUtil.getRefreshTokenKey(refreshToken);
        redisTemplate.opsForValue().set(key,userId.toString(),7,TimeUnit.DAYS);
        return tokenMap;
    }

    @Override
    public void resetPasswordByEmail(String email, String code, String newPassword) {
        if (code==null) throw new BlogException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        String key = "resetPassword" + email;
        String trueCode = redisTemplate.opsForValue().get(key);
        if (trueCode == null) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        if (!trueCode.equals(code)) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        user.setPassword(newPassword);
        userMapper.updateById(user);
        redisTemplate.delete(key);
    }

    @Override
    public void getCode(String email) {
        String code = CodeUtil.getRandomCode(4);
        System.out.println(code);
        String key = "resetPassword" + email;
        redisTemplate.opsForValue().set(key, code,5, TimeUnit.MINUTES);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        LoginUser loginUser = LoginUserHolder.get();
        User user = userMapper.selectById(loginUser.getUserId());
        if (!user.getPassword().equals(oldPassword)) throw new BlogException(ResultCodeEnum.ADMIN_PASSWORD_ERROR);
        user.setPassword(newPassword);
        userMapper.updateById(user);
    }

}
