package com.xiaoxin.blog.common.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiaoxin.blog.web.*.mapper")
public class MybatisConfiguraton {
}
