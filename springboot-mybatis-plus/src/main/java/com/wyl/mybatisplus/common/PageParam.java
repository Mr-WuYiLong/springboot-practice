package com.wyl.mybatisplus.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2023/11/13 16:50
 */
@Data
@ApiModel(value = "分页参数")
public class PageParam {
    @ApiModelProperty(value = "当前页")
    private Integer currentPage = 1;
    @ApiModelProperty(value = "页数")
    private Integer pageSize = 10;
    @ApiModelProperty(value = "排序字段")
    private String orderColumn;
    @ApiModelProperty(value = "排序方式，0:asc，1:desc")
    private Integer orderType = 1;
}