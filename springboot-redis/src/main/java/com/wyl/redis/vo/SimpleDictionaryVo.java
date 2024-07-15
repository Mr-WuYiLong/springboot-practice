package com.wyl.redis.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/11 16:34
 */
@Data
@ApiModel(value = "simpleDictionaryVo")
public class SimpleDictionaryVo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
