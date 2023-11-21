package com.jielim.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jielim.auth.mapper.SysMenuMapper;
import com.jielim.auth.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jielim.auth.utils.MenuHelper;
import com.jielim.common.config.exception.JielimException;
import com.jielim.common.result.ResultCodeEnum;
import com.office.model.system.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author jielim36
 * @since 2023-11-21
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    /*
     由于菜单是有层级的，类似于:
     第一层:
     1.系统管理
        第二层:
        1.用户管理
        2.角色管理
     2.产品管理
        1.xxx
        2.xxx

    所以使用递归变成：
    System Manage:[
        User Manage:[
        ...
        ],
        Role Manage:[
        ...
        ]
    ],
    Product Manage:[
        ...
    ]
     */
    @Override
    public List<SysMenu> findNodes() {
        //获取所有数据
        List<SysMenu> sysMenuList = baseMapper.selectList(null);

        //构建成树形结构(层级)
        List<SysMenu> resultList = MenuHelper.buildTree(sysMenuList);
        return resultList;
    }

    @Override
    public boolean removeMenuById(Long id) {

        //判断当前的Menu是否有Children，通过查询是否有某个Menu的ParentId是该Menu，如果有表示目前要删除的menu有children
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId,id);
        Integer childrenCount = baseMapper.selectCount(wrapper);
        if (childrenCount > 0){//大于0表示有某个Menu的Parent是目前要删除的Menu
            throw new JielimException(201,"Can't remove this menu because there are children menu...");
        }
        baseMapper.deleteById(id);
        return true;
    }
}
