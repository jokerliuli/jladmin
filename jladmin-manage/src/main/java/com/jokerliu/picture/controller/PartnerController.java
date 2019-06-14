package com.jokerliu.picture.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.picture.entity.Partner;
import com.jokerliu.picture.service.IPartnerService;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 合作伙伴图片管理 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Slf4j
@RestController
@RequestMapping("/picture/partner")
public class PartnerController {

 @Resource
 private  IPartnerService iPartnerService;

 /**
 * 单个新增
 * @param partner  传递的实体
 * @return Result
 */
 @PostMapping("save")
 public Result save(@RequestBody Partner partner){
     //log.info(partner.toString());
     return new Result(ResultStatusCode.OK,iPartnerService.save(partner));
 }

 /**
 * 单个删除
 * @param partner  传递的实体
 * @return Result
 */
 @PostMapping("remove")
 public Result remove(@RequestBody Partner partner) {
     return new Result(ResultStatusCode.OK,iPartnerService.remove(new QueryWrapper(partner)));
 }

 /**
 * 单个更新
 * @param partner  传递的实体
 * @return Result
 */
 @PostMapping("update")
 public Result update(@RequestBody Partner partner) {
     //partner.setUpdateDate(null);
     return new Result(ResultStatusCode.OK,iPartnerService.updateById(partner));
 }

 /**
 * 根据id查询获取一个返回
 * @param  id  对象id
 * @return Result
 */
 @GetMapping("getOne")
 public Result getOne(@RequestParam(name = "id") Long id){
     return new Result(ResultStatusCode.OK,iPartnerService.getById(id));
 }

 /**
 * 条件查询list
 * @param partner  传递的实体
 * @return Result
 */
 @PostMapping("list")
 public Result list( @RequestBody Partner partner) {
     return new Result(ResultStatusCode.OK,iPartnerService.list((Wrapper<Partner>) new QueryWrapper(partner).orderByAsc("sort")));
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
                    @RequestBody Partner partner) {
     IPage<Partner> partnerIPage = new Page<>();
     partnerIPage.setCurrent(page);
     partnerIPage.setSize(limit);
     return new Result(ResultStatusCode.OK,iPartnerService.page(partnerIPage, (Wrapper<Partner>) new QueryWrapper(partner).orderByAsc("sort")));
 }
}
