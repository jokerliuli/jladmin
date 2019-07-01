package com.jokerliu.system.entity;

import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 官网属性
 * </p>
 *
 * @author JokerLiu
 * @since 2019-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysWebsite extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 网站标题
     */
    private String title;

    /**
     * 简略标题
     */
    private String shortTitle;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 描述
     */
    private String webDescribe;


}
