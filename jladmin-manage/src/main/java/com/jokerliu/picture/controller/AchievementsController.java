package com.jokerliu.picture.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.picture.entity.Achievements;
import com.jokerliu.picture.service.IAchievementsService;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 我们的成果 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Slf4j
@RestController
@RequestMapping("/picture/achievements")
public class AchievementsController {

    @Resource
    private IAchievementsService iAchievementsService;

    /**
     * 单个新增
     *
     * @param achievements 传递的实体
     * @return Result
     */
    @PostMapping("save")
    public Result save(@RequestBody Achievements achievements) {
        //log.info(achievements.toString());
        return new Result(ResultStatusCode.OK, iAchievementsService.save(achievements));
    }

    /**
     * 单个删除
     *
     * @param achievements 传递的实体
     * @return Result
     */
    @PostMapping("remove")
    public Result remove(@RequestBody Achievements achievements) {
        return new Result(ResultStatusCode.OK, iAchievementsService.remove(new QueryWrapper(achievements)));
    }

    /**
     * 单个更新
     *
     * @param achievements 传递的实体
     * @return Result
     */
    @PostMapping("update")
    public Result update(@RequestBody Achievements achievements) {
        //achievements.setUpdateDate(null);
        return new Result(ResultStatusCode.OK, iAchievementsService.updateById(achievements));
    }

    /**
     * 根据id查询获取一个返回
     *
     * @param id 对象id
     * @return Result
     */
    @GetMapping("getOne")
    public Result getOne(@RequestParam(name = "id") Long id) {
        return new Result(ResultStatusCode.OK, iAchievementsService.getById(id));
    }

    /**
     * 条件查询list
     *
     * @param achievements 传递的实体
     * @return Result
     */
    @PostMapping("list")
    public Result list(@RequestBody Achievements achievements) {
        return new Result(ResultStatusCode.OK, iAchievementsService.list((Wrapper<Achievements>) new QueryWrapper(achievements).orderByAsc("sort")));
    }

    /**
     * 条件分页查询
     *
     * @param page  第几页（1开始）
     * @param limit 每页size
     * @return Result99999999999
     */
    @PostMapping("page")
    public Result page(@RequestParam(name = "page") int page,
                       @RequestParam(name = "limit") int limit,
                       @RequestBody Achievements achievements) {
        IPage<Achievements> achievementsIPage = new Page<>();
        achievementsIPage.setCurrent(page);
        achievementsIPage.setSize(limit);
        return new Result(ResultStatusCode.OK, iAchievementsService.page(achievementsIPage, (Wrapper<Achievements>) new QueryWrapper(achievements).orderByAsc("sort")));
    }
}
