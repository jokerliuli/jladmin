package com.jokerliu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jokerliu.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据UserId获得所有角色
     * @param id
     * @return List<SysRole>
     */
    List<SysRole>  findByUserid(Long id);

}
