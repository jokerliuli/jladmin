package com.jokerliu.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.system.entity.SysUser;
import com.jokerliu.system.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Resource
    private ISysUserService iSysUserService;

    /**
     * 新增
     *
     * @param sysUser 传递的实体
     * @return Result
     */

    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser) {
        if (iSysUserService.getOne(new QueryWrapper<SysUser>().eq("login_name", sysUser.getLoginName())) != null) {
            return new Result(ResultStatusCode.OK, "已存在该登录名");
        } else {
            return new Result(ResultStatusCode.OK, iSysUserService.save(sysUser));
        }

    }

    /**
     * 分页
     *
     * @param sysUser 传递的实体
     * @return Result
     */
    @PostMapping("page")
    public Result page(@RequestParam(name = "page") int page,
                       @RequestParam(name = "limit") int limit,
                       @RequestBody SysUser sysUser) {
        return new Result(ResultStatusCode.OK, iSysUserService.page(new Page<>(page, limit), new QueryWrapper(sysUser)));
    }

    /**
     * 单个更新
     *
     * @param sysUser 传递的实体
     * @return Result
     */
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        return new Result(ResultStatusCode.OK, iSysUserService.updateById(sysUser));
    }

    /**
     * 单个删除
     *
     * @param sysUser 传递的实体
     * @return Result
     */
    @PostMapping("remove")
    public Result remove(@RequestBody SysUser sysUser) {
        return new Result(ResultStatusCode.OK, iSysUserService.remove(new QueryWrapper(sysUser)));
    }
}
