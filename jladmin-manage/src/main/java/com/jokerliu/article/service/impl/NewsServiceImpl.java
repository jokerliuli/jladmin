package com.jokerliu.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokerliu.article.entity.News;
import com.jokerliu.article.mapper.NewsMapper;
import com.jokerliu.article.service.INewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 新闻动态表 服务实现类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Resource
    private NewsMapper newsMapper;

    /**
     * 增加一次访问量
     * @param id
     */
    @Override
    public void addVisit(Long id){
        newsMapper.addVisit(id);
    }

    @Override
    public Long getIdByRownum(int rownum) {
        return newsMapper.getIdByRownum(rownum);
    }

    @Override
    public int getRownumById(Long id) {
        return newsMapper.getRownumById(id);
    }
}
