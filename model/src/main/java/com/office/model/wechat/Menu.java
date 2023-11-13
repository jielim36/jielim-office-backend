package com.office.model.wechat;

import com.office.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wechat_menu")
public class Menu extends BaseEntity {

    @TableField("parent_id")
    private Long parentId;

    private String name;

    private String type;

    private String url;

    @TableField("meun_key")
    private String meunKey;

    private Integer sort;
}