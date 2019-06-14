package com.jokerliu.article.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 成功案例
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("article_success")
public class Success extends BaseEntity {

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
     * 内容项目背景
     */
    private String contentBackground;

    /**
     * 内容项目规划
     */
    private String contentPlane;

    /**
     * 内容建设内容
     */
    private String contentBuild;
    /**
     * 访问量
     */
    private Integer visit;

}
