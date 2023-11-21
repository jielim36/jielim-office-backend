package com.jielim.auth.controller;


import com.jielim.auth.service.SysMenuService;
import com.jielim.common.result.Result;
import com.office.model.system.SysMenu;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author jielim36
 * @since 2023-11-21
 */

@RestController
@Tag(name = "SysMenu Controller")
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @Autowired
    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @Operation(summary = "Get Menu Nodes")
    @GetMapping("")
    public Result findNodes(){
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    @Operation(summary = "Add new permission")
    @PostMapping("")
    public Result save(@RequestBody SysMenu permission){
        boolean isSuccess = sysMenuService.save(permission);
        return (isSuccess ? Result.ok() : Result.fail());
    }

    @Operation(summary = "Update permission by id")
    @PutMapping("")
    public Result updateById(@RequestBody SysMenu permission){
        boolean isSuccess = sysMenuService.updateById(permission);
        return (isSuccess ? Result.ok() : Result.fail());
    }

    @Operation(summary = "Remove permission by id")
    @DeleteMapping("")
    public Result removeById(@PathVariable Long id){
        boolean isSuccess = sysMenuService.removeById(id);
        return (isSuccess ? Result.ok() : Result.fail());
    }

}

