package com.jokerliu.config.shiro;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jokerliu.enums.CommonStatus;
import com.jokerliu.system.entity.SysMenu;
import com.jokerliu.system.entity.SysRole;
import com.jokerliu.system.entity.SysUser;
import com.jokerliu.system.service.ISysMenuService;
import com.jokerliu.system.service.ISysRoleService;
import com.jokerliu.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JokerLiu
 * @create 2018-12-29 10:36
 * @desc ShiroRealm
 **/

@Slf4j
public class ShiroRealm extends AuthorizingRealm {


    @Resource
    private ISysUserService iSysUserService;

    @Resource
    private ISysRoleService iSysRoleService;

    @Resource
    private ISysMenuService iSysMenuService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
            log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
            UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
            String name = token.getUsername();
            log.info("name:" + name);

            // 从数据库获取对应用户名密码的用户
            SysUser user = iSysUserService.getOne(new QueryWrapper<SysUser>().eq("login_name", name));
            log.info("user:" + user);

            if (user != null) {
                // 用户为禁用状态'数据状态 1可用，0不可用'
                if (CommonStatus.PROHIBIT.equals(user.getStatus())) {
                    throw new DisabledAccountException();
                }
                log.info("---------------- Shiro 凭证认证成功 ----------------------");
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                        //用户
                        user,
                        //密码
                        user.getPassword(),
                        //realm name
                        getName()
                );
                return authenticationInfo;
            }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal instanceof SysUser) {
            SysUser userLogin = (SysUser) principal;
            if(userLogin != null){
                List<SysRole> roleList = iSysRoleService.findByUserid(userLogin.getId());
                if(CollectionUtils.isNotEmpty(roleList)){
                    for(SysRole role : roleList){
                        log.info(role.getName());
                        info.addRole(role.getName());
                        List<SysMenu> menuList = iSysMenuService.getAllMenuByRoleId(role.getId());
                        if(CollectionUtils.isNotEmpty(menuList)){
                            for (SysMenu menu : menuList){
                                if(StrUtil.isNotBlank(menu.getMenuPermission())){
                                    info.addStringPermission(menu.getMenuPermission());
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("---------------- 获取到以下权限 ----------------");
        log.info(String.valueOf(info));
        log.info(info.getStringPermissions().toString());
        log.info("---------------- Shiro 权限获取成功 ----------------------");
        return info;

        //此处写死角色，需要可以自己完善
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addRole("admin");
//        info.addStringPermission("admin");
//        return info;

    }
}
