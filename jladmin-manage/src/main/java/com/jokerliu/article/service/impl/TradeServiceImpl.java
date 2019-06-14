package com.jokerliu.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokerliu.article.mapper.TradeMapper;
import com.jokerliu.article.entity.Trade;
import com.jokerliu.article.service.ITradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 行业动态表 服务实现类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements ITradeService {
    @Resource
    private TradeMapper tradeMapper;

    /**
     * 增加一次访问量
     * @param id
     */
    @Override
    public void addVisit(Long id){
        tradeMapper.addVisit(id);
    }

    @Override
    public Long getIdByRownum(int rownum) {
        return tradeMapper.getIdByRownum(rownum);
    }

    @Override
    public int getRownumById(Long id) {
        return tradeMapper.getRownumById(id);
    }
}
