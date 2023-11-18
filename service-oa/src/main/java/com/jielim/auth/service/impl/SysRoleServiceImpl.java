package com.jielim.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jielim.auth.mapper.SysRoleMapper;
import com.jielim.auth.service.SysRoleService;
import com.jielim.auth.service.SysUserRoleService;
import com.office.model.system.SysRole;
import com.office.model.system.SysUserRole;
import com.office.vo.system.AssignRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysUserRoleService sysUserRoleService;

    @Autowired
    public SysRoleServiceImpl(SysUserRoleService sysUserRoleService) {
        this.sysUserRoleService = sysUserRoleService;
    }

    /**
     * Query Role by user id (a user may have more than one role)
     * @param userId : compare with the SysUserRole Table's userId
     * @return : return a List that contains the roles of user id, and a list that contains all roles
     */
    @Override
    public Map<String, Object> findRoleByUserId(Long userId) {
        //query all role
        List<SysRole> allSysRoles = baseMapper.selectList(null);

        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        //the userId from method param will compare with the SysUserRole Table's userId
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> targetUserRoleList = sysUserRoleService.list(wrapper);

        //only return the role id list

//        List<Long> targetUserRoleIdList = new ArrayList<>();
//        for(SysUserRole sysUserRole : targetUserRoleList){
//            Long roleId = sysUserRole.getRoleId();
//            targetUserRoleIdList.add(roleId);
//        }

        //OR
        List<Long> targetUserRoleIdList = targetUserRoleList.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList());

        //Get Role by roleId and put all in a list
        List<SysRole> assignRoleList = new ArrayList<>();
        for (SysRole sysRole : allSysRoles){
            if (targetUserRoleIdList.contains(sysRole.getId())){
                assignRoleList.add(sysRole);
            }
        }

        Map<String , Object> roleMap = new HashMap<>();
        roleMap.put("assignRoleList",assignRoleList);
        roleMap.put("allRolesList",allSysRoles);
        return roleMap;
    }

    @Override
    public void assignUserRole(AssignRoleVo assignRoleVo) {
        //delete the role of the user assign previously
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, assignRoleVo.getUserId());
        sysUserRoleService.remove(wrapper);

        //Assign the new roles
        List<Long> roleIdList = assignRoleVo.getRoleIdList();
        for (Long roleId : roleIdList){
            if (StringUtils.isEmpty(roleId)){
                continue;
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assignRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
        }
    }

    //Automatic Dependencies Injection



}
