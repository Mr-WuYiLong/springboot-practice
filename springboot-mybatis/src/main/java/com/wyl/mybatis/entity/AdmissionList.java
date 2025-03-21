package com.wyl.mybatis.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 录取名单
 * @Author WuYiLong
 * @Date 2025-03-16
 */
@Data
public class AdmissionList{

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 大学
     */
    private String university;

    /**
     * 考生编号
     */
    private String examineeNo;

    /**
     * 考生姓名
     */
    private String name;

    /**
     * 院系
     */
    private String department;

    /**
     * 专业代码
     */
    private String professionalCode;

    /**
     * 初试总分
     */
    private Float initialExamScore;

    /**
     * 复试成绩
     */
    private Float interviewResultScore;

    /**
     * 总成绩
     */
    private Float totalScore;

    /**
     * 专业名称
     */
    private String professionalName;

    /**
     * 学习方式
     */
    private String studyType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 研究方向
     */
    private String researchDirection;

    /**
     * 学位类型
     */
    private String degreeType;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除？0否，1是
     */
    private Integer deleted;

}
