package com.office.vo.process;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class ProcessVo {

	private Long id;

	private Date createTime;

	private String processCode;

	private Long userId;
	private String name;

	@TableField("process_template_id")
	private Long processTemplateId;
	private String processTemplateName;

	private Long processTypeId;
	private String processTypeName;

	private String title;

	private String description;

	private String formProps;

	private String formOptions;

	private String formValues;

	private String processInstanceId;

	private String currentAuditor;

	private Integer status;

	private String taskId;

}