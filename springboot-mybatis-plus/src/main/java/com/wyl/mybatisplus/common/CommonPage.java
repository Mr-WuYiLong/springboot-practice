package com.wyl.mybatisplus.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Description 分页
 * @Author WuYiLong
 * @Date 2023/11/13 16:48
 */
@Data
@ApiModel(value = "commonPage")
public class CommonPage<T> {
    @ApiModelProperty(value = "当前页")
    private int currentPage = 1;
    @ApiModelProperty(value = "页数")
    private int pageSize = 10;
    @ApiModelProperty(value = "总量")
    private long total = 0L;
    @ApiModelProperty(value = "列表")
    private List<T> list = Collections.emptyList();

    public CommonPage(){};
    public CommonPage(Long currentPage,Long pageSize) {
        this.currentPage = currentPage.intValue();
        this.pageSize = pageSize.intValue();
    }
    public CommonPage(Long currentPage,Long pageSize,Long total) {
        this.currentPage = currentPage.intValue();
        this.pageSize = pageSize.intValue();
        this.total = total;
    }
}