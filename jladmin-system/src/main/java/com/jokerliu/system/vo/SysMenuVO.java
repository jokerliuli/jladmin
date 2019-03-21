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
    private String hidden;

    private MetaVO meta;

    @ApiModelProperty(value = "子列表")
    private List<SysMenuVO> children;

}
