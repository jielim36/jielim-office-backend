package com.jielim.auth;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jielim.auth.mapper.SysRoleMapper;
import com.jielim.auth.service.SysRoleService;
import com.office.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestMapperDemo01 {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void getAll(){
        //SELECT id,role_name,role_code,description,create_time,update_time,is_deleted FROM sys_role WHERE is_deleted=0
        //该查询只查询is_deleted为0表示未删除的数据
        //原理：BaseEntity中有使用@@TableLogic注解给is_deleted列
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        System.out.println(sysRoles);
    }

    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("Normal Admin");
        sysRole.setRoleCode("role");
        sysRole.setDescription("Normal Admin");

        int result = sysRoleMapper.insert(sysRole);
        System.out.println(result);
        System.out.println(sysRole);
    }

    @Test
    public void update(){
        SysRole sysRole = sysRoleMapper.selectById(9);
        sysRole.setRoleName("Jielim Office Admin");
        int result = sysRoleMapper.updateById(sysRole);
        System.out.println(result);
    }

    @Test
    public void delete(){
        int i = sysRoleMapper.deleteById(9);//这里使用的的逻辑删除(is_deleted更改为1表示已删除，但实际上依旧存在于数据库)
        //实际SQL语句：UPDATE sys_role SET is_deleted=1 WHERE id=? AND is_deleted=0
        //原理：BaseEntity中有使用@@TableLogic注解给is_deleted列

    }

    @Test
    public void deleteBatch(){
        int result = sysRoleMapper.deleteBatchIds(Arrays.asList(10, 11, 12));
        System.out.println(result);
    }

    @Test
    public void conditionQuery(){
        //create query wrapper object
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name" , "用户管理员");
        List<SysRole> list = sysRoleMapper.selectList(queryWrapper);
        System.out.println(list);

        //lambda version
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRole::getRoleName , "系统管理员");
        List<SysRole> sysRoles = sysRoleMapper.selectList(lambdaQueryWrapper);
        System.out.println(sysRoles);
    }

    //service version
    @Test
    public void service_getAll(){
        List<SysRole> sysRoles = sysRoleService.list();
        System.out.println(sysRoles);
    }

}

