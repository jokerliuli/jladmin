package com.jokerliu.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jokerliu.article.entity.Trade;

/**
 * <p>
 * 行业动态表 Mapper 接口
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
public interface TradeMapper extends BaseMapper<Trade> {
    /**
     * 增加一次访问量
     * @param id
     */
    void addVisit(Long id);

    Long getIdByRownum(int rownum);

    int getRownumById(Long id);
}
