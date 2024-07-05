package com.wyl.redis.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyl.redis.entity.AdsInduEtpEnterpriseStartNumDfNew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description 红皮云-企业发展分析-注册总量增长趋势分析
* @Author wuyilong
* @Date 2024-07-04
*/
@DS("star_rock")
@Mapper
public interface AdsInduEtpEnterpriseStartNumDfNewMapper extends BaseMapper<AdsInduEtpEnterpriseStartNumDfNew> {

    /**
     * 批量插入
     * @param list
     */
    void insertBatchAll(@Param("list") List<AdsInduEtpEnterpriseStartNumDfNew> list);

}
