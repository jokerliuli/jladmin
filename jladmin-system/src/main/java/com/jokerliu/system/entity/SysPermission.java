package com.jokerliu.system.entity;

import java.time.LocalDateTime;
import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 别名
     */
    private String alias;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级权限
     */
    private Integer pid;


}
