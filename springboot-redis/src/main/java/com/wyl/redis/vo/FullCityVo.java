package com.wyl.redis.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/9 15:55
 */
@Data
@ApiModel(value = "fullCityVo")
public class FullCityVo {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 行政编码
     */
    @ApiModelProperty("行政编码")
    private String code;

    /**
     * 全名称
     */
    @ApiModelProperty("全名称")
    private String fullName;

    /**
     * 级别，1省，2市，3区，4街道
     */
    @ApiModelProperty("级别")
    private Integer level;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 中心点
     */
    @ApiModelProperty("中心点")
    private String center;

    /**
     * 是否被撤销，0否，1是
     */
    @ApiModelProperty("是否被撤销")
    private Integer isRevoke;

    /**
     * 父级编码
     */
    @ApiModelProperty("父级编码")
    private String parentCode;
}
