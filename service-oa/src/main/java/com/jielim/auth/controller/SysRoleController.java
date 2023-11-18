package com.jielim.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jielim.common.config.exception.JielimException;
import com.jielim.common.result.Result;
import com.jielim.auth.service.SysRoleService;
import com.office.model.system.SysRole;
import com.office.vo.system.AssignRoleVo;
import com.office.vo.system.SysRoleQueryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Tag(name = "SysRole Controller")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @Autowired
    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Operation(summary = "get all sysRole data")
    @GetMapping("")
    public Result findAll(){
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    /**
     * This method use for get SysRole data by page number
     * @param page: current page
     * @param limit: how many data display in one page
     * @param sysRoleQueryVo : SysRole's value object use for transmit (Condition Object)
     * @return
     */
    @Operation(summary = "Get sysRole by condition page")
    @GetMapping("/{page}/{limit}")
    public Result pageQuerySysRole(@PathVariable Long page ,
                                   @PathVariable Long limit,
                                   SysRoleQueryVo sysRoleQueryVo){

        Page<SysRole> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)){
            lambdaQueryWrapper.like(SysRole::getRoleName,roleName);
        }

        IPage<SysRole> pageModel = sysRoleService.page(pageParam,lambdaQueryWrapper);
        return Result.ok(pageModel);
    }

    @Operation(summary = "Add Role")
    @PostMapping("")
    public Result addRole(@RequestBody SysRole sysRole){
        boolean is_success = sysRoleService.save(sysRole);
        if (is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @Operation(summary = "Update Role by id")
    @PutMapping("/{id}")
    public Result updateRole(@PathVariable Long id){
        SysRole sysRole = sysRoleService.getById(id);

        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new JielimException(20001,"Custom Exception Handler is executed...");
        }

        //Actually, there is no update anything
        if (sysRole != null){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @Operation(summary = "Update Role by object")
    @PutMapping("")
    public Result updateRoleByObject(@RequestBody SysRole newSysRole){
        boolean is_success = sysRoleService.updateById(newSysRole);
        if (is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @Operation(summary = "Delete Role by Id")
    @DeleteMapping("/{id}")
    public Result deleteRoleById(@PathVariable Long id){
        boolean is_success = sysRoleService.removeById(id);
        if (is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    /**
     *
     * @param idList : When the Delete request access here, must including the array that contains the id
     * @return
     */
    @Operation(summary = "Delete Role with Batch")
    @DeleteMapping()
    public Result deleteRoleBatch(@RequestBody List<Long> idList){
        boolean is_success = sysRoleService.removeByIds(idList);
//        if (is_success){
//            return Result.ok();
//        }else {
//            return Result.fail();
//        }
        return (is_success ? Result.ok() : Result.fail());
    }


    @Operation(summary = "Get User with user role")
    @GetMapping("/user/{userId}")
    public Result UserRole(@PathVariable Long userId){
        Map<String, Object> roleMapByUserId = sysRoleService.findRoleByUserId(userId);
        return Result.ok(roleMapByUserId);
    }

    @Operation(summary = "Get User with user role")
    @GetMapping("/role/{id}")
    public Result assignUserRole(@RequestBody AssignRoleVo assignRoleVo){
        sysRoleService.assignUserRole(assignRoleVo);
        return Result.ok();
    }



}
