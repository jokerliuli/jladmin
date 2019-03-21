package com.jokerliu.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.system.entity.SysPermission;
import com.jokerliu.system.mapper.SysPermissionMapper;
import com.jokerliu.system.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-05
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Resource
    private  SysPermissionMapper sysPermissionMapper;

    @Override
    public IPage<SysPermission> selectPage(Page page, SysPermission sysPermission) {
        return sysPermissionMapper.selectPage(page,sysPermission);
    }
}
