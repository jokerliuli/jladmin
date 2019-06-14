package com.jokerliu.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jokerliu.article.entity.News;

/**
 * <p>
 * 新闻动态表 服务类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
public interface INewsService extends IService<News> {
    /**
     * 增加一次访问量
     * @param id
     */
    void addVisit(Long id);

    Long getIdByRownum(int rownum);

    int getRownumById(Long id);
}
