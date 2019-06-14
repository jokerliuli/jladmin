package com.jokerliu.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jokerliu.article.entity.Success;

/**
 * <p>
 * 成功案例 服务类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
public interface ISuccessService extends IService<Success> {
    /**
     * 增加一次访问量
     * @param id
     */
    void addVisit(Long id);

    Long getIdByRownum(int rownum);

    int getRownumById(Long id);
}
