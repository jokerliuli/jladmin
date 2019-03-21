package com.jokerliu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jokerliu.system.entity.SysPermission;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-05
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    IPage<SysPermission> selectPage(Page page, SysPermission sysPermission);

}
