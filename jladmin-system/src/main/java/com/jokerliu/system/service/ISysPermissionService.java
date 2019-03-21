package com.jokerliu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jokerliu.system.entity.SysPermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-05
 */
public interface ISysPermissionService extends IService<SysPermission> {
    IPage<SysPermission> selectPage(Page page,SysPermission sysPermission);
}
