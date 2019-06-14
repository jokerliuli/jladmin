package com.jokerliu.service.impl;

import com.jokerliu.entity.Information;
import com.jokerliu.mapper.InformationMapper;
import com.jokerliu.service.IInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资讯表 服务实现类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-18
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements IInformationService {

}
