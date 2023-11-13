package com.office.vo.process;

import lombok.Data;

@Data
public class ProcessFormVo {

	private Long processTemplateId;

	private Long processTypeId;

	private String formValues;

}