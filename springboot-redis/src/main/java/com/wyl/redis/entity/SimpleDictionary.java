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
 * @Date 2024-07-11
 */
@Data
@TableName("simple_dictionary")
@Entity
@Table(name="simple_dictionary")
public class SimpleDictionary extends Model<SimpleDictionary> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("parentId")
    private Long parentId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
