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
 * @Description 红皮云-企业发展分析-注册总量增长趋势分析
 * @Author wuyilong
 * @Date 2024-07-04
 */
@Data
@TableName(value = "ads_indu_etp_enterprise_start_num_df_new",schema = "ads")
@Entity
@Table(name="ads_indu_etp_enterprise_start_num_df_new")
public class AdsInduEtpEnterpriseStartNumDfNew extends Model<AdsInduEtpEnterpriseStartNumDfNew> {

    private static final long serialVersionUID = 1L;

    /**
     * 企业注册量
     */
    @TableField("link_enterprise_star_num")
    private Long linkEnterpriseStarNum;

    /**
     * 注册年限
     */
    @TableField("start_year")
    private String startYear;

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
     * 产业链
     */
    @TableField("domain_link")
    private String domainLink;

    /**
     * 维度
     */
    @TableField("dim_type")
    private String dimType;

    /**
     * 地区id
     */
    @Id
    @TableField("region_id")
    private String regionId;


    @Override
    public Serializable pkVal() {
        return null;
    }

}
