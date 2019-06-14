package com.jokerliu.picture.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.picture.entity.Honor;
import com.jokerliu.picture.service.IHonorService;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 公司荣誉 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Slf4j
@RestController
@RequestMapping("/picture/honor")
public class HonorController {

 @Resource
 private IHonorService iHonorService;

 /**
 * 单个新增
 * @param honor  传递的实体
 * @return Result
 */
 @PostMapping("save")
 public Result save(@RequestBody Honor honor){
     //log.info(honor.toString());
     return new Result(ResultStatusCode.OK,iHonorService.save(honor));
 }

 /**
 * 单个删除
 * @param honor  传递的实体
 * @return Result
 */
 @PostMapping("remove")
 public Result remove(@RequestBody Honor honor) {
     return new Result(ResultStatusCode.OK,iHonorService.remove(new QueryWrapper(honor)));
 }

 /**
 * 单个更新
 * @param honor  传递的实体
 * @return Result
 */
 @PostMapping("update")
 public Result update(@RequestBody Honor honor) {
     //honor.setUpdateDate(null);
     return new Result(ResultStatusCode.OK,iHonorService.updateById(honor));
 }

 /**
 * 根据id查询获取一个返回
 * @param  id  对象id
 * @return Result
 */
 @GetMapping("getOne")
 public Result getOne(@RequestParam(name = "id") Long id){
     return new Result(ResultStatusCode.OK,iHonorService.getById(id));
 }

 /**
 * 条件查询list
 * @param honor  传递的实体
 * @return Result
 */
 @PostMapping("list")
 public Result list( @RequestBody Honor honor) {
     return new Result(ResultStatusCode.OK,iHonorService.list((Wrapper<Honor>) new QueryWrapper(honor).orderByAsc("sort")));
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
                    @RequestBody Honor honor) {
     IPage<Honor> honorIPage = new Page<>();
     honorIPage.setCurrent(page);
     honorIPage.setSize(limit);
     return new Result(ResultStatusCode.OK,iHonorService.page(honorIPage,new QueryWrapper(honor)));
 }
}
