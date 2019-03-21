package com.jokerliu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jokerliu.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据roleID获取所有菜单权限
     * @param roleId
     * @return List<SysMenu>
     */
    List<SysMenu> getAllMenuByRoleId(Long roleId);

    /**
     * 根据roleIDs获取前端左侧菜单(去重)
     * @param roleIds
     * @return List<SysMenu>
     */
    List<SysMenu> getMenus(List<Long> roleIds);

    /**
     * 直接获取所有菜单
     * @return List<SysMenu>
     */
    List<SysMenu> getAllMenu();
}
