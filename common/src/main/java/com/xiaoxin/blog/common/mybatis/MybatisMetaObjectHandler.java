package com.xiaoxin.blog.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        if (metaObject.hasGetter("deleted")) {
            Object deleted = getFieldValByName("deleted", metaObject);
            if (deleted != null && deleted.equals(1)) {
                this.strictUpdateFill(metaObject, "deleteTime", Date.class, new Date());
            }
        }
    }
}
