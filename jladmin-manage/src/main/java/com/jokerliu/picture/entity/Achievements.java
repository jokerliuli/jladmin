package com.jokerliu.picture.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 我们的成果
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("picture_achievements")
public class Achievements extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 绑定的成功案例id
     */
    private Long successId;

    /**
     * 图片存储在数据库的名称
     */
    private String pictureName;

    /**
     * 前端展示的图片标题
     */
    private String pictureTitle;

    /**
     * 图片分类
     */
    private String pictureType;

    /**
     * 前端展示的图片描述
     */
    private String pictureDescription;

    /**
     * 图片大小
     */
    private String pictureSize;

    /**
     * 图片尺寸长*宽
     */
    private String pictureDimension;

    /**
     * 图片url
     */
    private String pictureUrl;

    /**
     * 图片key
     */
    private String pictureKey;
    /**
     * 删除(0)草稿(1)发布(2)
     */
    private Integer publishStatus;

}
