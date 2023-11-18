package com.jielim.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.model.system.SysRole;
import com.office.vo.system.AssignRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    Map<String, Object> findRoleByUserId(Long userId);

    void assignUserRole(AssignRoleVo assignRoleVo);
}
