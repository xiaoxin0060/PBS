package com.xiaoxin.blog.web.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wf.captcha.SpecCaptcha;
import com.xiaoxin.blog.common.exception.BlogException;
import com.xiaoxin.blog.common.login.LoginUser;
import com.xiaoxin.blog.common.login.LoginUserHolder;
import com.xiaoxin.blog.common.result.ResultCodeEnum;
import com.xiaoxin.blog.common.utils.JwtUtil;
import com.xiaoxin.blog.model.entity.User;
import com.xiaoxin.blog.model.enums.UserRole;
import com.xiaoxin.blog.model.enums.UserStatus;
import com.xiaoxin.blog.web.app.dto.LoginDto;
import com.xiaoxin.blog.web.app.dto.RegisterDto;
import com.xiaoxin.blog.web.app.dto.ResetPasswordDto;
import com.xiaoxin.blog.web.app.mapper.UserMapper;
import com.xiaoxin.blog.web.app.service.AuthService;
import com.xiaoxin.blog.web.app.vo.CaptchaVo;
import com.xiaoxin.blog.web.app.vo.EmailCodeVo;
import com.xiaoxin.blog.web.app.vo.LoginVo;
import com.xiaoxin.blog.web.app.vo.TokenVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AuthServiceImpl implements AuthService{
    // Redis Key前缀
    private static final String CAPTCHA_KEY_PREFIX = "auth-captcha-";
    private static final String TOKEN_BLACKLIST_PREFIX = "token-blacklist-";
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginVo register(RegisterDto registerDto)
    {
        // 1. 校验验证码
        String captchaCode = redisTemplate.opsForValue().get(registerDto.getCaptchaKey());
        if(captchaCode == null){
            throw new BlogException(ResultCodeEnum.APP_CAPTCHA_CODE_EXIST);
        }
        if(!captchaCode.equals(registerDto.getCaptcha().toLowerCase())){
            throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        // 2. 校验用户名是否存在
        LambdaQueryWrapper<User> usernameWrapper = new LambdaQueryWrapper<>();
        usernameWrapper.eq(User::getUsername, registerDto.getUsername());
        if(userMapper.selectCount(usernameWrapper) > 0){
            throw new BlogException(ResultCodeEnum.APP_USERNAME_EXIST_ERROR);
        }

        // 3. 校验邮箱是否存在
        LambdaQueryWrapper<User> emailWrapper = new LambdaQueryWrapper<>();
        emailWrapper.eq(User::getEmail, registerDto.getEmail());
        if(userMapper.selectCount(emailWrapper) > 0){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_EXIST_ERROR);
        }

        // 4. 创建用户
        User user = new User();
        BeanUtils.copyProperties(registerDto, user);
        user.setPassword(registerDto.getPassword());
        user.setRole(UserRole.USER.getCode());
        user.setStatus(UserStatus.NORMAL.getCode());

        userMapper.insert(user);

        // 5. 生成Token
        String token = JwtUtil.creatJwt(user.getId(), user.getUsername());
        String refreshToken = JwtUtil.creatRefreshToken(user.getId(), user.getUsername());

        // 6. 清除验证码
        redisTemplate.delete(registerDto.getCaptchaKey());

        // 7. 构建返回结果
        return buildLoginVo(user, token, refreshToken);
    }

    @Override
    public LoginVo login(LoginDto loginDto)
    {
        // 1. 校验验证码
        String captchaCode = redisTemplate.opsForValue().get(loginDto.getCaptchaKey());
        if(captchaCode == null){
            throw new BlogException(ResultCodeEnum.APP_CAPTCHA_CODE_EXIST);
        }
        if(!captchaCode.equals(loginDto.getCaptcha().toLowerCase())){
            throw new BlogException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        // 2. 查找用户（支持用户名或邮箱登录）
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w -> w.eq(User::getUsername, loginDto.getUsername()).or()
                          .eq(User::getEmail, loginDto.getUsername()));

        User user = userMapper.selectOne(wrapper);
        if(user == null){
            throw new BlogException(ResultCodeEnum.APP_USER_NOT_EXIST);
        }

        // 3. 校验密码
        if(!Objects.equals(user.getPassword(), loginDto.getPassword())){
            throw new BlogException(ResultCodeEnum.APP_PASSWORD_ERROR);
        }

        // 4. 校验用户状态
        if(Objects.equals(user.getStatus(), UserStatus.DISABLED.getCode())){
            throw new BlogException(ResultCodeEnum.APP_USER_DISABLED);
        }

        // 5. 生成Token（根据记住我设置过期时间）
        String token;
        if(loginDto.getRememberMe() != null && loginDto.getRememberMe()){
            token = JwtUtil.creatJwtWithExpiration(user.getId(), user.getUsername(), 604800L);
        }else{
            token = JwtUtil.creatJwt(user.getId(), user.getUsername());
        }
        String refreshToken = JwtUtil.creatRefreshToken(user.getId(), user.getUsername());

        // 6. 清除验证码
        redisTemplate.delete(loginDto.getCaptchaKey());

        // 7. 构建返回结果
        return buildLoginVo(user, token, refreshToken);
    }


    @Override
    public void logout()
    {
        // 1. 获取当前登录用户（从ThreadLocal中）
        LoginUser currentUser = LoginUserHolder.get();
        if(currentUser == null){
            // 未登录状态，直接返回
            return;
        }
        // 2. 清除用户相关缓存
        Long userId = currentUser.getUserId();
        if(userId != null){
            // 清除用户信息缓存
            redisTemplate.delete("user-info-" + userId);
        }

        // 注意：Token黑名单处理已在拦截器中实现
        // 可以在这里添加注销日志记录
        System.out.println("用户注销：userId=" + userId + ", username=" + currentUser.getUsername());
    }

    @Override
    public TokenVo refreshToken(String refreshToken)
    {
        // 1. 校验RefreshToken是否为空
        if(refreshToken == null || refreshToken.trim().isEmpty()){
            throw new BlogException(ResultCodeEnum.APP_REFRESH_TOKEN_NOT_EXIST);
        }

        // 2. 校验RefreshToken是否有效
        if(!JwtUtil.isTokenValid(refreshToken)){
            throw new BlogException(ResultCodeEnum.APP_REFRESH_TOKEN_EXPIRED);
        }

        // 3. 检查RefreshToken是否在黑名单中
        if(Boolean.TRUE.equals(redisTemplate.hasKey(TOKEN_BLACKLIST_PREFIX + refreshToken))){
            throw new BlogException(ResultCodeEnum.APP_REFRESH_TOKEN_EXPIRED);
        }

        // 4. 从RefreshToken中获取用户信息
        Long userId = JwtUtil.getUserIdFromToken(refreshToken);
        String username = JwtUtil.getUsernameFromToken(refreshToken);

        // 5. 查询用户信息（校验用户是否存在且正常）
        User user = userMapper.selectById(userId);
        if(user == null){
            throw new BlogException(ResultCodeEnum.APP_USER_NOT_EXIST);
        }
        if(Objects.equals(user.getStatus(), UserStatus.DISABLED.getCode())){
            throw new BlogException(ResultCodeEnum.APP_USER_DISABLED);
        }

        // 6. 生成新的Token和RefreshToken
        String newToken = JwtUtil.creatJwt(userId, username);
        String newRefreshToken = JwtUtil.creatRefreshToken(userId, username);

        // 7. 将旧的RefreshToken加入黑名单
        long expiration = JwtUtil.getTokenExpiration(refreshToken);
        if(expiration > 0){
            redisTemplate.opsForValue().set(TOKEN_BLACKLIST_PREFIX + refreshToken,
                    "refresh",
                    expiration,
                    TimeUnit.SECONDS);
        }

        // 8. 构建返回结果
        TokenVo tokenVo = new TokenVo();
        tokenVo.setToken(newToken);
        tokenVo.setRefreshToken(newRefreshToken);
        tokenVo.setTokenType("Bearer");
        tokenVo.setExpiresIn(JwtUtil.getDefaultExpiration());
        tokenVo.setRefreshTime(new Date());

        return tokenVo;
    }

    @Override
    public CaptchaVo getCaptcha()
    {
        // 1. 生成验证码（使用你现有的SpecCaptcha）
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        String code = specCaptcha.text().toLowerCase();
        String key = "auth-captcha-" + UUID.randomUUID();
        String imageBase64 = specCaptcha.toBase64();

        // 2. 存储到Redis（10分钟过期）
        redisTemplate.opsForValue().set(key, code, 10, TimeUnit.MINUTES);

        // 3. 构建返回结果
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setCaptchaKey(key);
        captchaVo.setCaptchaImage(imageBase64);
        captchaVo.setCaptchaType("login"); // 固定类型，或者根据需要设置
        captchaVo.setExpireTime(600L); // 10分钟 = 600秒
        captchaVo.setCreateTime(new Date()); // 使用Date类型

        return captchaVo;
    }

    @Override
    public EmailCodeVo sendResetPasswordCode(String email)
    {
        // 1. 校验邮箱格式
        if(email == null || email.trim().isEmpty()){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_INVALID);
        }

        // 简单邮箱格式校验
        if(!email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$")){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_INVALID);
        }

        // 2. 校验邮箱是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        User user = userMapper.selectOne(wrapper);
        if(user == null){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_NOT_EXIST);
        }

        // 3. 校验用户状态
        if(Objects.equals(user.getStatus(), UserStatus.DISABLED.getCode())){
            throw new BlogException(ResultCodeEnum.APP_USER_DISABLED);
        }

        // 4. 检查发送频率限制（60秒内只能发送一次）
        String rateLimitKey = "reset-password-rate-limit-" + email;
        if(redisTemplate.hasKey(rateLimitKey)){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_CODE_SEND_FREQUENTLY);
        }

        // 5. 生成6位随机验证码
        String code = String.format("%06d", (int) (Math.random() * 1000000));

        // 6. 存储验证码到Redis（5分钟过期）
        String codeKey = "reset-password-code-" + email;
        redisTemplate.opsForValue().set(codeKey, code, 5, TimeUnit.MINUTES);

        // 7. 设置发送频率限制（60秒）
        redisTemplate.opsForValue().set(rateLimitKey, "1", 60, TimeUnit.SECONDS);

        // 8. TODO: 实际发送邮件（这里暂时模拟）
        // emailService.sendResetPasswordCode(email, code);
        System.out.println("发送重置密码验证码到邮箱: " + email + ", 验证码: " + code);

        // 9. 构建返回结果
        EmailCodeVo emailCodeVo = new EmailCodeVo();
        emailCodeVo.setSuccess(true);
        emailCodeVo.setMessage("验证码已发送到您的邮箱，请查收");
        emailCodeVo.setExpireMinutes(5); // 5分钟过期
        emailCodeVo.setNextSendInterval(60); // 60秒后可再次发送
        emailCodeVo.setSendTime(LocalDateTime.now());

        return emailCodeVo;
    }

    @Override
    public void resetPassword(ResetPasswordDto resetPasswordDto)
    {
        // 注意：由于使用了@Valid注解，基本的参数校验会在Controller层完成
        // 这里主要处理业务逻辑校验

        // 1. 校验验证码
        String codeKey = "reset-password-code-" + resetPasswordDto.getEmail();
        String storedCode = redisTemplate.opsForValue().get(codeKey);
        if(storedCode == null){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_CODE_EXPIRED);
        }
        if(!storedCode.equals(resetPasswordDto.getVerificationCode())){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_CODE_ERROR);
        }

        // 2. 查找用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, resetPasswordDto.getEmail());
        User user = userMapper.selectOne(wrapper);
        if(user == null){
            throw new BlogException(ResultCodeEnum.APP_EMAIL_NOT_EXIST);
        }

        // 3. 校验用户状态
        if(Objects.equals(user.getStatus(), UserStatus.DISABLED.getCode())){
            throw new BlogException(ResultCodeEnum.APP_USER_DISABLED);
        }

        // 4. 校验新密码不能与当前密码相同
        if(Objects.equals(user.getPassword(), resetPasswordDto.getNewPassword())){
            throw new BlogException(ResultCodeEnum.APP_PASSWORD_SAME_AS_OLD);
        }

        // 5. 更新密码（开发环境直接存储明文）
        user.setPassword(resetPasswordDto.getNewPassword());
        userMapper.updateById(user);

        // 6. 清除验证码
        redisTemplate.delete(codeKey);

        // 7. 清除该邮箱的发送频率限制
        redisTemplate.delete("reset-password-rate-limit-" + resetPasswordDto.getEmail());

        // 8. 可选：将该用户的所有Token加入黑名单（强制重新登录）
        // 由于无法获取到用户的所有Token，这里暂不实现
        // 在生产环境中，可以考虑在用户表中加入token_version字段来实现全局Token失效

        System.out.println("用户重置密码成功: email=" + resetPasswordDto.getEmail());
    }

    /**
     * 构建登录返回对象
     *
     * @param user         用户实体
     * @param token        访问令牌
     * @param refreshToken 刷新令牌
     * @return 登录响应VO
     */
    private LoginVo buildLoginVo(User user, String token, String refreshToken)
    {
        LoginVo loginVo = new LoginVo();

        // Token信息
        loginVo.setToken(token);
        loginVo.setRefreshToken(refreshToken);
        loginVo.setTokenType("Bearer");
        loginVo.setExpiresIn(JwtUtil.getDefaultExpiration());
        loginVo.setLoginTime(LocalDateTime.now()); // 这个可以保持LocalDateTime

        // 用户信息 - 直接复制属性，类型完全匹配
        LoginVo.UserInfoVo userInfo = new LoginVo.UserInfoVo();
        BeanUtils.copyProperties(user, userInfo); // 可以直接用BeanUtils了
        userInfo.setRoleName(UserRole.getRoleName(user.getRole())); // 只需要设置计算字段

        loginVo.setUserInfo(userInfo);
        return loginVo;
    }
}

