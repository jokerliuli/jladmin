package com.jokerliu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokerliu.system.entity.SysMenu;
import com.jokerliu.system.entity.SysRole;
import com.jokerliu.system.mapper.SysMenuMapper;
import com.jokerliu.system.service.ISysMenuService;
import com.jokerliu.system.vo.MetaVO;
import com.jokerliu.system.vo.SysMenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getAllMenuByRoleId(Long roleId) {
        return sysMenuMapper.getAllMenuByRoleId(roleId);
    }

    @Override
    public List<SysMenuVO> getAllMenu() {
        return creatMenuTree(sysMenuMapper.getAllMenu());
    }

    @Override
    public List<SysMenuVO> getMenus(List<SysRole> roleList) {
        //获取到所有菜单entity的List
        List<Long> roleIds = new ArrayList<>();
        for (SysRole role : roleList) {
            roleIds.add(role.getId());
        }
        List<SysMenu> menuList = sysMenuMapper.getMenus(roleIds);
        return creatMenuTree(menuList);
    }

    /**
     * list转tree
     * @param menuList
     * @return
     */
    private List<SysMenuVO> creatMenuTree(List<SysMenu> menuList){
        List<SysMenuVO> menuVOTree = new ArrayList<>();
        List<SysMenuVO> menuVOList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            SysMenuVO menuVO = new SysMenuVO();
            BeanUtils.copyProperties(menu, menuVO);
            //构建前台路由所需meta属性
            MetaVO meta = new MetaVO();
            if (menu.getMetaTitle() != null) {
                meta.setTitle(menu.getMetaTitle());
            }
            if (menu.getMetaIcon() != null) {
                meta.setIcon(menu.getMetaIcon());
            }
            if (menu.getMetaNocache() != null) {
                meta.setNoCache(menu.getMetaNocache());
            }
            if (menu.getMetaBreadcrumb() != null) {
                meta.setBreadcrumb(menu.getMetaBreadcrumb());
            }
            menuVO.setMeta(meta);
            menuVOList.add(menuVO);
        }
        //遍历volist
        for (SysMenuVO menuVO : menuVOList) {
            if (menuVO.getParentId() == 0) {
                menuVO.setLevel(1);
                //递归
                menuVOTree.add(createTreeChildren(menuVO, menuVOList));
            }
        }
        return menuVOTree;
    }
    private SysMenuVO createTreeChildren(SysMenuVO menuVO, List<SysMenuVO> menuVOList) {
        for (SysMenuVO menuVO2 : menuVOList) {
            if (menuVO2.getParentId() == menuVO.getId()) {
                menuVO2.setLevel(menuVO.getLevel()+1);
                menuVO2.setParentName(menuVO.getName());
                if (menuVO.getChildren() == null) {
                    menuVO.setChildren(new ArrayList<>());
                }
                menuVO.getChildren().add(createTreeChildren(menuVO2, menuVOList));
            }
        }
        return menuVO;
    }
}
