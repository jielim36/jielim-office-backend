package com.office.model.system;

import com.office.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@TableField("role_id")
	private Long roleId;

	@TableField("menu_id")
	private Long menuId;

}

