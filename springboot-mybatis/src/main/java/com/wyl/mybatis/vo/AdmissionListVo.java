package com.wyl.mybatis.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
* @Description 录取名单
* @Author WuYiLong
* @Date 2025-03-16
*/
@Data
@ApiModel(value = "AdmissionListVo对象", description = "录取名单")
public class AdmissionListVo implements Serializable {
private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "大学")
    private String university;

    @ApiModelProperty(value = "考生编号")
    private String examineeNo;

    @ApiModelProperty(value = "考生姓名")
    private String name;

    @ApiModelProperty(value = "院系")
    private String department;

    @ApiModelProperty(value = "专业代码")
    private String professionalCode;

    @ApiModelProperty(value = "初试总分")
    private Float initialExamScore;

    @ApiModelProperty(value = "复试成绩")
    private Float interviewResultScore;

    @ApiModelProperty(value = "总成绩")
    private Float totalScore;

    @ApiModelProperty(value = "专业名称")
    private String professionalName;

    @ApiModelProperty(value = "学习方式")
    private String studyType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "年份")
    private Integer year;

    @ApiModelProperty(value = "研究方向")
    private String researchDirection;

    @ApiModelProperty(value = "学位类型")
    private String degreeType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除？0否，1是")
    private Integer deleted;
}
