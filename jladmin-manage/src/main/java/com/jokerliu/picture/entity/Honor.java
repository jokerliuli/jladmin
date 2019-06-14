package com.jokerliu.picture.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司荣誉
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("picture_honor")
public class Honor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 删除(0)草稿(1)发布(2)
     */
    private Integer publishStatus;

    /**
     * 文章内容
     */
    private String content;




}
