package com.jokerliu.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资讯表
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("manage_information")
public class Information extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 简略标题
     */
    private String shortTitle;

    /**
     * 分类栏目：新闻动态(1)，产品方案(2)，成功案例(3) 
     */
    private Integer informationType;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 删除(0)草稿(1)发布(2)
     */
    private Integer publishStatus;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 文章内容
     */
    private String content;


}
