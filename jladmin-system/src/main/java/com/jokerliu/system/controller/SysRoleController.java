package com.jokerliu.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.enums.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.system.entity.SysRole;
import com.jokerliu.system.service.ISysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {
    @Resource
    private ISysRoleService iSysRoleService;
    /**
     * 单个新增
     * @param sysRole  传递的实体
     * @return Result
     */
    @PostMapping("save")
    public Result save(@RequestBody SysRole sysRole) {
        return new Result(ResultStatusCode.OK,iSysRoleService.save(sysRole));
    }
    /**
     * 分页查询
     * @param page,limit,sysUser
     * @return Result
     */
    @PostMapping("page")
    public Result page(@RequestParam(name = "page") int page,
                       @RequestParam(name = "limit") int limit,
                       @RequestBody SysRole sysRole) {
        return new Result(ResultStatusCode.OK,iSysRoleService.page(new Page<>(page,limit),new QueryWrapper(sysRole)));
    }

    /**
     * 单个更新
     * @param sysRole  传递的实体
     * @return Result
     */
    @PostMapping("update")
    public Result update(@RequestBody SysRole sysRole) {
        return new Result(ResultStatusCode.OK,iSysRoleService.updateById(sysRole));
    }

    /**
     * 单个删除
     * @param sysRole  传递的实体
     * @return Result
     */
    @PostMapping("remove")
    public Result remove(@RequestBody SysRole sysRole) {
        return new Result(ResultStatusCode.OK,iSysRoleService.remove(new QueryWrapper(sysRole)));
    }
}
