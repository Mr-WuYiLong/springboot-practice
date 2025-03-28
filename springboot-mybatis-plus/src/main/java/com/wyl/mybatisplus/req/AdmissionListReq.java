package com.wyl.mybatisplus.req;

import com.wyl.mybatisplus.common.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 录取名单
 * @Author WuYiLong
 * @Date 2025-03-16
 */
@Data
@ApiModel(value = "AdmissionListReq对象", description = "录取名单")
public class AdmissionListReq extends PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "大学")
    private String university;

    @ApiModelProperty(value = "年份")
    private Integer year;

}
