package com.jokerliu.article.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.article.entity.Trade;
import com.jokerliu.article.service.ITradeService;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 行业动态表 前端控制器
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
@Slf4j
@RestController
@RequestMapping("/article/trade")
public class TradeController {

    @Resource
    private ITradeService iTradeService;

    /**
     * 根据id让访问量+1
     * @param  id  对象id
     * @return Result
     */
    @GetMapping("addVisit")
    public Result addVisit(@RequestParam(name = "id") Long id){
        iTradeService.addVisit(id);
        return new Result(ResultStatusCode.OK);
    }


    /**
     * 单个新增
     *
     * @param trade 传递的实体
     * @return Result
     */
    @PostMapping("save")
    public Result save(@RequestBody Trade trade) {
        //log.info(trade.toString());
        return new Result(ResultStatusCode.OK, iTradeService.save(trade));
    }

    /**
     * 单个删除
     *
     * @param trade 传递的实体
     * @return Result
     */
    @PostMapping("remove")
    public Result remove(@RequestBody Trade trade) {
        return new Result(ResultStatusCode.OK, iTradeService.remove(new QueryWrapper(trade)));
    }

    /**
     * 单个更新
     *
     * @param trade 传递的实体
     * @return Result
     */
    @PostMapping("update")
    public Result update(@RequestBody Trade trade) {
        //trade.setUpdateDate(null);
        return new Result(ResultStatusCode.OK, iTradeService.updateById(trade));
    }

    /**
     * 根据id查询获取一个返回
     *
     * @param id 对象id
     * @return Result
     */
    @GetMapping("getOne")
    public Result getOne(@RequestParam(name = "id") Long id) {
        return new Result(ResultStatusCode.OK, iTradeService.getById(id));
    }

    /**
     * 条件查询list
     *
     * @param trade 传递的实体
     * @return Result
     */
    @PostMapping("list")
    public Result list(@RequestBody Trade trade) {
        QueryWrapper<Trade> queryWrapper = new QueryWrapper(trade);
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByDesc("create_date");
        return new Result(ResultStatusCode.OK, iTradeService.list(queryWrapper));
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
                       @RequestBody Trade trade) {
        IPage<Trade> tradeIPage = new Page<>();
        tradeIPage.setCurrent(page);
        tradeIPage.setSize(limit);
        QueryWrapper<Trade> queryWrapper = new QueryWrapper(trade);
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByDesc("create_date");
        return new Result(ResultStatusCode.OK, iTradeService.page(tradeIPage, queryWrapper));
    }
}
