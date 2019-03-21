package com.jokerliu.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jokerliu.enums.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.system.entity.SysMenu;
import com.jokerliu.system.service.ISysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    @Resource
    private ISysMenuService iSysMenuService;

    /**
     * 新增菜单
     * @param sysMenu
     * @return Result
     */
    @PostMapping("save")
    public Result save(SysMenu sysMenu) {
        return new Result(ResultStatusCode.OK,iSysMenuService.save(sysMenu));
    }

    /**
     * 查询所有菜单
     * @return Result
     */
    @GetMapping("list")
    public Result list() {
        return new Result(ResultStatusCode.OK,iSysMenuService.getAllMenu());
    }

    /**
     * 单个更新
     * @param sysMenu  传递的实体
     * @return Result
     */
    @PostMapping("update")
    public Result update(@RequestBody SysMenu sysMenu) {
        return new Result(ResultStatusCode.OK,iSysMenuService.updateById(sysMenu));
    }

    /**
     * 单个删除
     * @param sysMenu  传递的实体
     * @return Result
     */
    @PostMapping("remove")
    public Result remove(@RequestBody SysMenu sysMenu) {
        return new Result(ResultStatusCode.OK,iSysMenuService.remove(new QueryWrapper(sysMenu)));
    }

}
