package com.wyl.mybatis.req;

import com.wyl.mybatis.common.PageParam;
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
public class AdmissionListReq  extends PageParam {

}
