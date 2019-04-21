package com.zxa.backgroud.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_secondary_category")
public class SecondCategory {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    @TableField("name")
    private String  name;
    /**
     * 一级分类ID
     */
    @TableField("category_id")
    private Long categoryId;
    /**
     * 状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
}
