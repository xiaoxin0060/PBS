package com.xiaoxin.blog.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;
@Data
public class BaseEntity {

       /**
     * 删除状态标识
     * 0: 未删除
     * 1: 已删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 删除时间
     * 记录数据被删除的具体时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date deleteTime;

    /**
     * 创建时间
     * 记录数据首次创建的时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     * 记录数据最后一次更新的时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
