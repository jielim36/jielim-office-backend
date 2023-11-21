package com.jielim.auth.service.impl;

import com.jielim.auth.mapper.SysMenuMapper;
import com.jielim.auth.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jielim.auth.utils.MenuHelper;
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
}
