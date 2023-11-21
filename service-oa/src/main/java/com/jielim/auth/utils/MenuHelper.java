package com.jielim.auth.utils;

import com.office.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

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
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {

        List<SysMenu> trees = new ArrayList<>();

        for (SysMenu sysMenu : sysMenuList) {
            //递归入口
            if (sysMenu.getParentId().longValue() == 0){//0表示顶部，比如System Management和Product Management
                trees.add(getChildren(sysMenu,sysMenuList));
            }
        }

        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu , List<SysMenu> sysMenuList){
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单数据，判断id和parentId对应的关系
        for (SysMenu it : sysMenuList){
            if (sysMenu.getId().longValue() == it.getParentId().longValue()){
                if (sysMenu.getChildren() == null){//如果到尽头了，就给他创建一个新的存放Children的ArrayList，避免最后一个Node的Children是null
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildren(it,sysMenuList));
            }
        }
        return sysMenu;
    }

}
