package com.wyl.redis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 红皮云-企业发展分析-历年注册企业统计
 * @Author wuyilong
 * @Date 2024-07-04
 */
@Data
@TableName(value = "ads_indu_etp_enterprise_login_num_df",schema = "ads")
@Entity
@Table(name="ads_indu_etp_enterprise_login_num_df")
public class AdsInduEtpEnterpriseLoginNumDf extends Model<AdsInduEtpEnterpriseLoginNumDf> {

    private static final long serialVersionUID = 1L;

    /**
     * 产业链
     */
    @TableField("domain_link")
    private String domainLink;

    /**
     * 地区id
     */
    @TableField("region_id")
    private String regionId;

    /**
     * 企业注册量
     */
    @TableField("link_enterprise_login_num")
    private Long linkEnterpriseLoginNum;

    /**
     * 注册年限
     */
    @TableField("login_year")
    private String loginYear;

    /**
     * 省id
     */
    @TableField("province_id")
    private String provinceId;

    /**
     * 市id
     */
    @TableField("city_id")
    private String cityId;

    /**
     * 区id
     */
    @TableField("area_id")
    private String areaId;

    /**
     * 省
     */
    @TableField("province")
    private String province;

    /**
     * 市
     */
    @TableField("city")
    private String city;

    /**
     * 区
     */
    @TableField("area")
    private String area;

    /**
     * 维度
     */
    @Id
    @TableField("dim_type")
    private String dimType;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
