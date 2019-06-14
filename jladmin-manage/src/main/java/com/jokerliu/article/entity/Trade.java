package com.jokerliu.article.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行业动态表
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("article_trade")
public class Trade extends BaseEntity {

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

    /**
     * 访问量
     */
    private Integer visit;
}
