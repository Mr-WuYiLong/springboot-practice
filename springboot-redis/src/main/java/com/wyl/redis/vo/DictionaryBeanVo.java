package com.wyl.redis.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/10 11:48
 */
@Data
@ApiModel(value = "dictionaryBeanVo")
public class DictionaryBeanVo {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "级别")
    private Integer level;

    @ApiModelProperty(value = "父类编码")
    private String parentCode;

    @ApiModelProperty(value = "额外参数")
    private Map<String,Object> extraParamMap;
}
