package com.jokerliu.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.enums.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.manage.entity.Information;
import com.jokerliu.manage.service.IInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 资讯表 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-05
 */
@Slf4j
@RestController
@RequestMapping("/manage/information")
public class InformationController {

    @Resource
    private  IInformationService iInformationService;

    /**
    * 单个新增
    * @param information  传递的实体
    * @return Result
    */
    @PostMapping("save")
    public Result save(@RequestBody Information information){
        //log.info(information.toString());
        return new Result(ResultStatusCode.OK,iInformationService.save(information));
    }

    /**
    * 单个删除
    * @param information  传递的实体
    * @return Result
    */
    @PostMapping("remove")
    public Result remove(@RequestBody Information information) {
        return new Result(ResultStatusCode.OK,iInformationService.remove(new QueryWrapper(information)));
    }

    /**
    * 单个更新
    * @param information  传递的实体
    * @return Result
    */
    @PostMapping("update")
    public Result update(@RequestBody Information information) {
        return new Result(ResultStatusCode.OK,iInformationService.updateById(information));
    }

    /**
    * 根据id查询获取一个返回
    * @param  id  对象id
    * @return Result
    */
    @GetMapping("getOne")
    public Result getOne(@RequestParam(name = "id") Long id){
        return new Result(ResultStatusCode.OK,iInformationService.getById(id));
    }

    /**
    * 条件查询list
    * @param information  传递的实体
    * @return Result
    */
    @PostMapping("list")
    public Result list( @RequestBody Information information) {
        return new Result(ResultStatusCode.OK,iInformationService.list(new QueryWrapper(information)));
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
                       @RequestBody Information information) {
        return new Result(ResultStatusCode.OK,iInformationService.page(new Page<>(page,limit),new QueryWrapper(information)));
    }
}
