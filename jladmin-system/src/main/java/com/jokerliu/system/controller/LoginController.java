package com.jokerliu.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.system.entity.SysRole;
import com.jokerliu.system.entity.SysUser;
import com.jokerliu.system.service.ISysMenuService;
import com.jokerliu.system.service.ISysRoleService;
import com.jokerliu.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author JokerLiu
 * @create 2018-12-29 10:36
 * @desc 登陆controller
 **/
@Slf4j
@RestController
public class LoginController {


    @Resource
    private ISysRoleService iSysRoleService;
    @Resource
    private ISysMenuService iSysMenuService;

    @Resource
    private ISysUserService iSysUserService;

    @PostMapping("/login")
    public Result login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) {
        try {
            log.info("username:" + username + "," + "password:" + password);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);

            if (subject.isAuthenticated()) {
                JSONObject json = new JSONObject();
                json.put("token", subject.getSession().getId());
                log.info("json:" + json);
                return new Result(ResultStatusCode.OK, json);
            } else {
                return new Result(ResultStatusCode.SHIRO_ERROR);
            }
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            return new Result(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        } catch (LockedAccountException e) {
            return new Result(ResultStatusCode.USER_FROZEN);
        } catch (Exception e) {
            return new Result(ResultStatusCode.SYSTEM_ERR);
        }
    }

    @GetMapping("admin/info")
    public Result getCurrentUserInfo() {
        Subject sub = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) sub.getPrincipal();
        JSONObject json = new JSONObject();
        json.put("avatar", sysUser.getHeadImg());
        json.put("name", sysUser.getTrueName());
        List<SysRole> roleList = iSysRoleService.findByUserid(sysUser.getId());
        json.put("roles", roleList);
        log.info("cuser/info/json:" + json);
        return new Result(ResultStatusCode.OK, json);
    }

//    @GetMapping("admin/
//    ")
//    public Result getMenu() {
//        Subject subject = SecurityUtils.getSubject();
//        SysMenuVO allmenus = (SysMenuVO) subject.getPrincipal();
//        log.info(String.valueOf(allmenus));
//        return new Result(ResultStatusCode.OK, iSysMenuService.getMenu(Long.valueOf("1"), Long.valueOf("0")));
//    }

    @PostMapping("admin/menuByRoles")
    public Result getMenus(@RequestBody List<SysRole> roleList) {
        return new Result(ResultStatusCode.OK, iSysMenuService.getMenus(roleList));
    }


    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping("admin/logout")
    public Result logout() {
        log.info("进入logout");
        SecurityUtils.getSubject().logout();
        return new Result(ResultStatusCode.OK);
    }

//



}
