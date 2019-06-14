package com.jokerliu.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokerliu.article.entity.Success;
import com.jokerliu.article.service.ISuccessService;
import com.jokerliu.article.mapper.SuccessMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 成功案例 服务实现类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Service
public class SuccessServiceImpl extends ServiceImpl<SuccessMapper, Success> implements ISuccessService {
    @Resource
    private SuccessMapper successMapper;

    /**
     * 增加一次访问量
     * @param id
     */
    @Override
    public void addVisit(Long id){
        successMapper.addVisit(id);
    }

    @Override
    public Long getIdByRownum(int rownum) {
        return successMapper.getIdByRownum(rownum);
    }

    @Override
    public int getRownumById(Long id) {
        return successMapper.getRownumById(id);
    }
}
