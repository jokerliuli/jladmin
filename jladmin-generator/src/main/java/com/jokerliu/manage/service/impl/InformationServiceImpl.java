package com.jokerliu.manage.service.impl;

import com.jokerliu.manage.entity.Information;
import com.jokerliu.manage.mapper.InformationMapper;
import com.jokerliu.manage.service.IInformationService;
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
