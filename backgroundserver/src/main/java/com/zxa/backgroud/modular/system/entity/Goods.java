package com.zxa.backgroud.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_goods")
public class Goods {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @TableField("category_id")
    private Long categoryId;
    @TableField("price")
    private Float price;
    @TableField("simple_describe")
    private String simpleDescribe;
    @TableField("complex_describe")
    private String complexDescribe;
    @TableField("inventory")
    private Integer inventory;
    @TableField(value = "sales_quantity",fill = FieldFill.INSERT)
    private Integer salesQuantity;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
}
