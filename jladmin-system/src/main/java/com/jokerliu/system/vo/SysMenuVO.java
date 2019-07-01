package com.jokerliu.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SysMenuVO {
    private Long id;
    private Long parentId;
    private String path;
    private String component;
    private String redirect;
    private String alwaysshow;
    private String name;
    private String parentName;
    private String hidden;
    private Integer sort;
//  菜单级别从1开始
    private Integer level;
    private MetaVO meta;
    private String menuType;
    @ApiModelProperty(value = "子列表")
    private List<SysMenuVO> children;

}
