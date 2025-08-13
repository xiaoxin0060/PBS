package com.xiaoxin.blog.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum{

    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    PARAM_ERROR(202, "参数不正确"),
    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    ILLEGAL_REQUEST(205, "非法请求"),
    REPEAT_SUBMIT(206, "重复提交"),
    DELETE_ERROR(207, "请先删除子集"),

    ADMIN_ACCOUNT_EXIST_ERROR(301, "账号已存在"),
    ADMIN_CAPTCHA_CODE_ERROR(302, "验证码错误"),
    ADMIN_CAPTCHA_CODE_EXPIRED(303, "验证码已过期"),
    ADMIN_CAPTCHA_CODE_NOT_FOUND(304, "未输入验证码"),
    ADMIN_LOGIN_AUTH(305, "未登陆"),
    ADMIN_ACCOUNT_NOT_EXIST_ERROR(306, "账号不存在"),
    ADMIN_ACCOUNT_ERROR(307, "用户名或密码错误"),
    ADMIN_ACCOUNT_DISABLED_ERROR(308, "该用户已被禁用"),
    ADMIN_ACCESS_FORBIDDEN(309, "无访问权限"),
    ADMIN_PASSWORD_ERROR(310, "密码错误"),

    APP_LOGIN_AUTH(501, "未登陆"),
    APP_LOGIN_PHONE_EMPTY(502, "手机号码为空"),
    APP_LOGIN_CODE_EMPTY(503, "验证码为空"),
    APP_SEND_SMS_TOO_OFTEN(504, "验证法发送过于频繁"),
    APP_LOGIN_CODE_EXPIRED(505, "验证码已过期"),
    APP_LOGIN_CODE_ERROR(506, "验证码错误"),
    APP_ACCOUNT_DISABLED_ERROR(507, "该用户已被禁用"),
    APP_USERNAME_EXIST_ERROR(508, "用户名已存在"),
    APP_CAPTCHA_CODE_EXIST(509, "验证码不存在"),
    APP_EMAIL_EXIST_ERROR(510, "邮箱已存在"),

    TOKEN_EXPIRED(601, "token过期"),
    TOKEN_INVALID(602, "token非法"),
    APP_REFRESH_TOKEN_NOT_EXIST(602,",RefreshToken不能为空"),
    APP_REFRESH_TOKEN_EXPIRED(603,"RefreshToken已过期"),

    ARTICLE_NOT_EXIST(701, "文章不存在"),
    DATA_EXIST(702, "数据已存在，请勿重复添加"),
    APP_PASSWORD_ERROR(511, "密码错误"),
    APP_USER_NOT_EXIST(512, "用户不存在"),
    APP_USER_DISABLED(513, "用户被禁用"),

    APP_EMAIL_INVALID(511,"邮箱格式不正确"),
    APP_EMAIL_NOT_EXIST(512,"邮箱不存在"),
    APP_EMAIL_CODE_SEND_FREQUENTLY(513,"验证码发送过于频繁，请稍后再试"),
    APP_EMAIL_CODE_INVALID(514,"验证码不能为空"),
    APP_EMAIL_CODE_EXPIRED(515,"验证码已过期"),
    APP_EMAIL_CODE_ERROR(516,"验证码错误"),
    APP_PASSWORD_INVALID(517,"密码不能为空"),
    APP_PASSWORD_SAME_AS_OLD(518, "新密码不能与旧密码相同"),
    APP_DATA_NOT_EXIST(519, "访问不存在或是没有权限的数据"),
    APP_NO_AUTH(520,"没有权限访问" ),
    APP_DATA_CONFLICT(521, "数据冲突");


    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
