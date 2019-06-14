package com.jokerliu.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jokerliu.article.entity.News;

/**
 * <p>
 * 新闻动态表 Mapper 接口
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
public interface NewsMapper extends BaseMapper<News> {
    /**
     * 增加一次访问量
     * @param id
     */
    void addVisit(Long id);

    Long getIdByRownum(int rownum);

    int getRownumById(Long id);
}
