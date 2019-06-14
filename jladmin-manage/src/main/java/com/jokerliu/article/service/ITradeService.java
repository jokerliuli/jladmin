package com.jokerliu.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jokerliu.article.entity.Trade;

/**
 * <p>
 * 行业动态表 服务类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
public interface ITradeService extends IService<Trade> {
    /**
     * 增加一次访问量
     * @param id
     */
    void addVisit(Long id);

    Long getIdByRownum(int rownum);

    int getRownumById(Long id);
}
