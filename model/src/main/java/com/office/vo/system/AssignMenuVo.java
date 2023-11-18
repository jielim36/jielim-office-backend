package com.office.vo.system;


import lombok.Data;

import java.util.List;

@Data
public class AssignMenuVo {

    private Long roleId;

    private List<Long> menuIdList;

}
