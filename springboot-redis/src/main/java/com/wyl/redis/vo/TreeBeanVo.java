package com.wyl.redis.vo;

import com.wyl.redis.bean.TreeBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/10 14:03
 */
@Data
@ApiModel(value = "treeBeanVo")
public class TreeBeanVo {

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "父级编码")
    private String parentCode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "子节点")
    private List<TreeBean> children;
}
