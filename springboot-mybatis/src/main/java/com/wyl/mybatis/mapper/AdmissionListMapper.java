package com.wyl.mybatis.mapper;

import com.wyl.mybatis.dto.AdmissionListDto;
import com.wyl.mybatis.entity.AdmissionList;
import com.wyl.mybatis.req.AdmissionListReq;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2025/3/16 16:57
 */
@Mapper
public interface AdmissionListMapper {

    /**
     * 分页
     * @param req
     * @return
     */
    List<AdmissionList> selectPage(@Param("req") AdmissionListReq req);

    /**
     * 详情
     * @param id
     * @return
     */
    @ResultMap(value = "BaseResultMap")
    @Select("select * from admission_list where id = #{id}")
    AdmissionList selectById(@Param("id") Serializable id);

    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from admission_list where id = #{id}")
    void deleteById(@Param("id") Serializable id);

    /**
     * 根据id批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 插入
     * @param admissionList
     */
    void insert(AdmissionList admissionList);

    /**
     * 更新
     * @param admissionList
     */
    void updateById(@Param("item") AdmissionList admissionList);



}