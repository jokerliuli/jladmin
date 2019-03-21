package com.jokerliu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jokerliu.system.entity.SysRole;
import com.jokerliu.system.mapper.SysRoleMapper;
import com.jokerliu.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author alex
 * @since 2018-12-11
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findByUserid(Long id) {
        return sysRoleMapper.findByUserId(id);
    }
}
