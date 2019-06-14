package com.jokerliu.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jokerliu.article.entity.Success;

/**
 * <p>
 * 成功案例 Mapper 接口
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
public interface SuccessMapper extends BaseMapper<Success> {
    /**
     * 增加一次访问量
     * @param id
     */
    void addVisit(Long id);

    Long getIdByRownum(int rownum);

    int getRownumById(Long id);
}
