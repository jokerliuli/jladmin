package com.jokerliu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.system.entity.SysWebsite;
import com.jokerliu.system.service.ISysWebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 官网属性 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-05-06
 */
@Slf4j
@RestController
@RequestMapping("/system/website")
public class SysWebsiteController {

    @Resource
    private ISysWebsiteService iSysWebsiteService;

    /**
     * 单个新增
     *
     * @param sysWebsite 传递的实体
     * @return Result
     */
    @PostMapping("save")
    public Result save(@RequestBody SysWebsite sysWebsite) {
        //log.info(sysWebsite.toString());
        return new Result(ResultStatusCode.OK, iSysWebsiteService.save(sysWebsite));
    }

    /**
     * 单个删除
     *
     * @param sysWebsite 传递的实体
     * @return Result
     */
    @PostMapping("remove")
    public Result remove(@RequestBody SysWebsite sysWebsite) {
        return new Result(ResultStatusCode.OK, iSysWebsiteService.remove(new QueryWrapper(sysWebsite)));
    }

    /**
     * 单个更新
     *
     * @param sysWebsite 传递的实体
     * @return Result
     */
    @PostMapping("update")
    public Result update(@RequestBody SysWebsite sysWebsite) {
        //sysWebsite.setUpdateDate(null);
        return new Result(ResultStatusCode.OK, iSysWebsiteService.updateById(sysWebsite));
    }

    /**
     * 根据id查询获取一个返回
     *
     * @param id 对象id
     * @return Result
     */
    @GetMapping("getOne")
    public Result getOne(@RequestParam(name = "id") Long id) {
        return new Result(ResultStatusCode.OK, iSysWebsiteService.getById(id));
    }

    /**
     * 条件查询list
     *
     * @param sysWebsite 传递的实体
     * @return Result
     */
    @PostMapping("list")
    public Result list(@RequestBody SysWebsite sysWebsite) {
        return new Result(ResultStatusCode.OK, iSysWebsiteService.list(new QueryWrapper(sysWebsite)));
    }

    /**
     * 条件分页查询
     *
     * @param page  第几页（1开始）
     * @param limit 每页size
     * @return Result
     */
    @PostMapping("page")
    public Result page(@RequestParam(name = "page") int page,
                       @RequestParam(name = "limit") int limit,
                       @RequestBody SysWebsite sysWebsite) {
        IPage<SysWebsite> sysWebsiteIPage = new Page<>();
        sysWebsiteIPage.setCurrent(page);
        sysWebsiteIPage.setSize(limit);
        return new Result(ResultStatusCode.OK, iSysWebsiteService.page(sysWebsiteIPage, new QueryWrapper(sysWebsite)));
    }
}
