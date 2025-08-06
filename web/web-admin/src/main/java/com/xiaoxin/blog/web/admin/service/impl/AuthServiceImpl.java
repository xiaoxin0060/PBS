package com.xiaoxin.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.common.utils.JwtUtil;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.web.admin.mapper.UserMapper;
import com.xiaoxin.blog.web.admin.service.AuthService;
import com.xiaoxin.blog.web.admin.vo.AuthVo;
import com.xiaoxin.blog.web.admin.vo.CaptchaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
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
    public String login(AuthVo authVo) {
        if(authVo.getCaptchaCode()== null) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        String code = redisTemplate.opsForValue().get(authVo.getCaptchaKey());
        if(code==null) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        if(!code.equals(authVo.getCaptchaCode())) throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, authVo.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(user==null) throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        if (user.getStatus()==1) throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        if(!user.getPassword().equals(authVo.getPassword())) throw new BlogException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        return JwtUtil.creatJwt(user.getId(),user.getUsername());
    }

}
