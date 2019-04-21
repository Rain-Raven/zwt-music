package com.zxa.backgroud.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_goods_image")
public class GoodsImage {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "goods_id")
    private Long goodsId;
    @TableField(value = "image")
    private String image;
    @TableField(value = "type")
    private Integer type;
    @TableField(value = "update_time",fill= FieldFill.UPDATE)
    private Date updateTime;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
}
