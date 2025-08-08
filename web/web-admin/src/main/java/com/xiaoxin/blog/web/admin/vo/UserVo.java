package com.xiaoxin.blog.web.admin.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVo {
    private Long id;

    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    private String password;

    @Email(message="邮箱格式错误")
    @NotBlank(message="邮箱不能为空")
    private String email;

    private String avatar;
}
