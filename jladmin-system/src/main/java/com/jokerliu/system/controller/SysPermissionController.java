package com.jokerliu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.system.entity.SysPermission;
import com.jokerliu.system.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-05
 */
@Slf4j
@RestController
@RequestMapping("/system/permission")
public class SysPermissionController {

    @Resource
    private  ISysPermissionService iSysPermissionService;

    /**
    * 单个新增
    * @param sysPermission  传递的实体
    * @return Result
    */
    @PostMapping("save")
    public Result save(@RequestBody SysPermission sysPermission){
        //log.info(sysPermission.toString());
        return new Result(ResultStatusCode.OK,iSysPermissionService.save(sysPermission));
    }

    /**
    * 单个删除
    * @param sysPermission  传递的实体
    * @return Result
    */
    @PostMapping("remove")
    public Result remove(@RequestBody SysPermission sysPermission) {
        return new Result(ResultStatusCode.OK,iSysPermissionService.remove(new QueryWrapper(sysPermission)));
    }

    /**
    * 单个更新
    * @param sysPermission  传递的实体
    * @return Result
    */
    @PostMapping("update")
    public Result update(@RequestBody SysPermission sysPermission) {
        //sysPermission.setUpdateDate(null);
        return new Result(ResultStatusCode.OK,iSysPermissionService.updateById(sysPermission));
    }

    /**
    * 根据id查询获取一个返回
    * @param  id  对象id
    * @return Result
    */
    @GetMapping("getOne")
    public Result getOne(@RequestParam(name = "id") Long id){
        return new Result(ResultStatusCode.OK,iSysPermissionService.getById(id));
    }

    /**
    * 条件查询list
    * @param sysPermission  传递的实体
    * @return Result
    */
    @PostMapping("list")
    public Result list( @RequestBody SysPermission sysPermission) {
        return new Result(ResultStatusCode.OK,iSysPermissionService.list(new QueryWrapper(sysPermission)));
    }

    /**
    * 条件分页查询
    * @param  page  第几页（1开始）
    * @param  limit  每页size
    * @return Result
    */
    @PostMapping("page")
    public Result page(@RequestParam(name = "page") int page,
                       @RequestParam(name = "limit") int limit,
                       @RequestBody SysPermission sysPermission) {
//        IPage<SysPermission> sysPermissionIPage = new Page<>();
//        sysPermissionIPage.setCurrent(page);
//        sysPermissionIPage.setSize(limit);
        return new Result(ResultStatusCode.OK,iSysPermissionService.page(new Page<>(page,limit),new QueryWrapper(sysPermission)));
    }

    /**
     * 条件分页查询
     * @return Result
     */
    @GetMapping("selectPage")
    public Result selectPage() {
        Page<SysPermission> sysPermissionIPage = new Page<>();
        sysPermissionIPage.setCurrent(1);
        sysPermissionIPage.setSize(10);
        SysPermission SysPermission = new SysPermission();
        return new Result(ResultStatusCode.OK, iSysPermissionService.selectPage(sysPermissionIPage, SysPermission));
    }
}
