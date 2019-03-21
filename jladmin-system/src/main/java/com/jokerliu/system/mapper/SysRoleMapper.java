package com.jokerliu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jokerliu.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据userid来获取他所有的roles
     * @param id
     * @return List<SysRole>
     */
    List<SysRole> findByUserId(Long id);

}
