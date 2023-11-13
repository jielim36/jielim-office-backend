package com.office.model.process;

import com.office.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oa_process_record")
public class ProcessRecord extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableField("process_id")
	private Long processId;

	@TableField("description")
	private String description;

	@TableField("status")
	private Integer status;

	@TableField("operate_user_id")
	private Long operateUserId;

	@TableField("operate_user")
	private String operateUser;

}