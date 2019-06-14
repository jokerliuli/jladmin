package com.jokerliu.picture.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jokerliu.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合作伙伴图片管理
 * </p>
 *
 * @author JokerLiu
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("picture_partner")
public class Partner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 前端展示的图片标题
     */
    private String pictureTitle;


    /**
     * 前端展示的图片描述
     */
    private String pictureDescription;

    /**
     * 图片url
     */
    private String pictureUrl;

    /**
     * 悬停图片url
     */
    private String hoverPictureUrl;

    /**
     * 图片key
     */
    private String pictureKey;

    /**
     * 悬停图片key
     */
    private String hoverPictureKey;


    /**
     * 删除(0)草稿(1)发布(2)
     */
    private Integer publishStatus;

}
