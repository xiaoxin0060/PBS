package com.xiaoxin.blog.common.login;

public class LoginUserHolder {
    private static final ThreadLocal<LoginUser> loginUser = new ThreadLocal<>();
    public static void set(LoginUser user) {
        loginUser.set(user);
    }
    public static LoginUser get() {
        return loginUser.get();
    }
    public static void remove() {
        loginUser.remove();
    }
}
