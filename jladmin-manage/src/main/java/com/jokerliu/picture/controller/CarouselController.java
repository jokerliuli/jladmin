package com.jokerliu.picture.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.picture.entity.Carousel;
import com.jokerliu.picture.service.ICarouselService;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 轮播图片 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
@Slf4j
@RestController
@RequestMapping("/picture/carousel")
public class CarouselController {

 @Resource
 private  ICarouselService iCarouselService;

 /**
 * 单个新增
 * @param carousel  传递的实体
 * @return Result
 */
 @PostMapping("save")
 public Result save(@RequestBody Carousel carousel){
     //log.info(carousel.toString());
     return new Result(ResultStatusCode.OK,iCarouselService.save(carousel));
 }

 /**
 * 单个删除
 * @param carousel  传递的实体
 * @return Result
 */
 @PostMapping("remove")
 public Result remove(@RequestBody Carousel carousel) {
     return new Result(ResultStatusCode.OK,iCarouselService.remove(new QueryWrapper(carousel)));
 }

 /**
 * 单个更新
 * @param carousel  传递的实体
 * @return Result
 */
 @PostMapping("update")
 public Result update(@RequestBody Carousel carousel) {
     //carousel.setUpdateDate(null);
     return new Result(ResultStatusCode.OK,iCarouselService.updateById(carousel));
 }

 /**
 * 根据id查询获取一个返回
 * @param  id  对象id
 * @return Result
 */
 @GetMapping("getOne")
 public Result getOne(@RequestParam(name = "id") Long id){
     return new Result(ResultStatusCode.OK,iCarouselService.getById(id));
 }

 /**
 * 条件查询list
 * @param carousel  传递的实体
 * @return Result
 */
 @PostMapping("list")
 public Result list( @RequestBody Carousel carousel) {
     return new Result(ResultStatusCode.OK,iCarouselService.list((Wrapper<Carousel>) new QueryWrapper(carousel).orderByAsc("sort")));
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
                    @RequestBody Carousel carousel) {
     IPage<Carousel> carouselIPage = new Page<>();
     carouselIPage.setCurrent(page);
     carouselIPage.setSize(limit);
     return new Result(ResultStatusCode.OK,iCarouselService.page(carouselIPage, (Wrapper<Carousel>) new QueryWrapper(carousel).orderByAsc("sort")));
 }
}
