package com.jokerliu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jokerliu.system.entity.SysMenu;
import com.jokerliu.system.entity.SysRole;
import com.jokerliu.system.vo.SysMenuVO;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据roleId获得所有权限
     *
     * @param roleId
     * @return List<SysMenu>
     */
    List<SysMenu> getAllMenuByRoleId(Long roleId);

    /**
     * 获取所有menu树状返回
     *
     * @return Set<SysMenu>
     */
    List<SysMenuVO> getAllMenu();
    /**
     * 根据roleList获取前端左侧菜单(去重)
     *
     * @param roleList
     * @return List<SysMenu>
     */
    List<SysMenuVO> getMenus(List<SysRole> roleList);
}
