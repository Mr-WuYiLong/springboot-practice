package com.wyl.redis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description 
 * @Author wuyilong
 * @Date 2024-07-03
 */
@Data
@TableName("full_city")
@Entity
@Table(name="full_city")
public class FullCity extends Model<FullCity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 行政编码
     */
    @TableField("code")
    private String code;

    /**
     * 全名称
     */
    @TableField("full_name")
    private String fullName;

    /**
     * 级别，1省，2市，3区，4街道
     */
    @TableField("level")
    private Integer level;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 中心点
     */
    @TableField("center")
    private String center;

    /**
     * 是否被撤销，0否，1是
     */
    @TableField("is_revoke")
    private Integer isRevoke;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
