package com.wyl.redis.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/4 9:30
 */
@Data
@ApiModel(value = "dictionaryBean")
public class DictionaryBean{
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "级别")
    private Integer level = 0;

    @ApiModelProperty(value = "父类编码")
    private String parentCode;
}
