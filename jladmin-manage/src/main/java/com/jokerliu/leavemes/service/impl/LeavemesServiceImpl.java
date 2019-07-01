package com.jokerliu.leavemes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jokerliu.leavemes.entity.Leavemes;
import com.jokerliu.leavemes.mapper.LeavemesMapper;
import com.jokerliu.leavemes.service.ILeavemesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
@Service
public class LeavemesServiceImpl extends ServiceImpl<LeavemesMapper, Leavemes> implements ILeavemesService {

}
