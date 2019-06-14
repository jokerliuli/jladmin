package com.jokerliu.article.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.article.entity.Success;
import com.jokerliu.article.service.ISuccessService;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 成功案例 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Slf4j
@RestController
@RequestMapping("/article/success")
public class SuccessController {

    @Resource
    private ISuccessService iSuccessService;

    /**
     * 根据id让访问量+1
     *
     * @param id 对象id
     * @return Result
     */
    @GetMapping("addVisit")
    public Result addVisit(@RequestParam(name = "id") Long id) {
        iSuccessService.addVisit(id);
        return new Result(ResultStatusCode.OK);
    }

    /**
     * 根据id让访问量+1
     *
     * @param id 对象id
     * @return Result
     */
    @GetMapping("count")
    public Result count(@RequestParam(name = "id") Long id) {
        return new Result(ResultStatusCode.OK, iSuccessService.count(new QueryWrapper<Success>().eq("id", id)));
    }

    /**
     * 单个新增
     *
     * @param success 传递的实体
     * @return Result
     */
    @PostMapping("save")
    public Result save(@RequestBody Success success) {
        //log.info(success.toString());
        return new Result(ResultStatusCode.OK, iSuccessService.save(success));
    }

    /**
     * 单个删除
     *
     * @param success 传递的实体
     * @return Result
     */
    @PostMapping("remove")
    public Result remove(@RequestBody Success success) {
        return new Result(ResultStatusCode.OK, iSuccessService.remove(new QueryWrapper(success)));
    }

    /**
     * 单个更新
     *
     * @param success 传递的实体
     * @return Result
     */
    @PostMapping("update")
    public Result update(@RequestBody Success success) {
        //success.setUpdateDate(null);
        return new Result(ResultStatusCode.OK, iSuccessService.updateById(success));
    }

    /**
     * 根据id查询获取一个返回
     *
     * @param id 对象id
     * @return Result
     */
    @GetMapping("getOne")
    public Result getOne(@RequestParam(name = "id") Long id) {
        return new Result(ResultStatusCode.OK, iSuccessService.getById(id));
    }

    /**
     * 条件查询list
     *
     * @param success 传递的实体
     * @return Result
     */
    @PostMapping("list")
    public Result list(@RequestBody Success success) {
        QueryWrapper<Success> queryWrapper = new QueryWrapper(success);
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByDesc("create_date");
        return new Result(ResultStatusCode.OK, iSuccessService.list(queryWrapper));
    }

    /**
     * 根据id查询获取一个返回
     *
     * @param id 对象id
     * @return Result
     */
    @GetMapping("getPageNum")
    public Result getPageNum(@RequestParam(name = "id") Long id) {
        Success success = iSuccessService.getById(id);
        QueryWrapper<Success> queryWrapper = new QueryWrapper();
        queryWrapper.eq("publish_status",2);
        queryWrapper.lt("sort",success.getSort());
        int a = iSuccessService.count(queryWrapper);
        queryWrapper.eq("sort",success.getSort());
        queryWrapper.gt("create_date",success.getCreateDate());
        int b = iSuccessService.count(queryWrapper);
        int page = a+b+1;
        System.out.println(page);
        return new Result(ResultStatusCode.OK,page);
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
                       @RequestBody Success success) {
        IPage<Success> successIPage = new Page<>();
        successIPage.setCurrent(page);
        successIPage.setSize(limit);
        QueryWrapper<Success> queryWrapper = new QueryWrapper(success);
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByDesc("create_date");
        return new Result(ResultStatusCode.OK, iSuccessService.page(successIPage, queryWrapper));
    }
}
