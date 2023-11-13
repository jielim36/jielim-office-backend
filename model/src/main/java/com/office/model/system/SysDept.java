package com.office.model.system;

import com.office.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_dept")
public class SysDept extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableField("name")
	private String name;

	@TableField("parent_id")
	private Long parentId;

	@TableField("tree_path")
	private String treePath;

	@TableField("sort_value")
	private Integer sortValue;

	@TableField("leader")
	private String leader;

	@TableField("phone")
	private String phone;

	@TableField("status")
	private Integer status;

	@TableField(exist = false)
	private List<SysDept> children;

}