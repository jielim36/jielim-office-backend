package com.jielim.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jielim.auth.service.SysUserService;
import com.jielim.common.result.Result;
import com.office.model.system.SysUser;
import com.office.vo.system.AssignRoleVo;
import com.office.vo.system.SysUserQueryVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jielim36
 * @since 2023-11-17
 */
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Operation(summary = "User condition page query")
    @GetMapping("/{page}/{limit}")
    public Result getUsersByPage(@PathVariable Long page,
                        @PathVariable Long limit,
                        SysUserQueryVo sysUserQueryVo){

        Page<SysUser> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        String username = sysUserQueryVo.getKeyword();
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();
        if (!StringUtils.isEmpty(username)){
            wrapper.like(SysUser::getName,username);
        }
        if (!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge(SysUser::getCreateTime , createTimeBegin);//SysUser.createTime > createTimeBegin
        }
        if (!StringUtils.isEmpty(createTimeEnd)){
            wrapper.le(SysUser::getCreateTime , createTimeEnd);//SysUser.createTime < createTimeEnd
        }

        Page<SysUser> pageModal = sysUserService.page(pageParam, wrapper);
        return Result.ok(pageModal);
    }

    @Operation(summary = "Get a user")
    @GetMapping("/{id}")
    public Result getUser(@PathVariable Long id){
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @Operation(summary = "Save a user")
    @PostMapping("")
    public Result saveUser(@RequestBody SysUser sysUser){
        boolean isSuccess = sysUserService.save(sysUser);
        return (isSuccess ? Result.ok() : Result.fail());
    }

    @Operation(summary = "update user by id")
    @PutMapping("")
    public Result updateUserById(@RequestBody SysUser sysUser){
        boolean isSuccess = sysUserService.updateById(sysUser);
        return (isSuccess ? Result.ok() : Result.fail());
    }

    @Operation(summary = "remove user by id")
    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable Long id){
        boolean isSuccess = sysUserService.removeById(id);
        return (isSuccess ? Result.ok() : Result.fail());
    }

    @Operation(summary = "batch remove user")
    @DeleteMapping("")
    public Result batchDeleteUser(@RequestBody List<Long> idList){
        boolean isSuccess = sysUserService.removeByIds(idList);
        return (isSuccess ? Result.ok() : Result.fail());
    }





}

