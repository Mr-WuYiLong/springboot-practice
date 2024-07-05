package com.wyl.redis.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyl.redis.entity.AdsInduEtpEnterpriseNumDf;
import org.apache.ibatis.annotations.Mapper;

/**
* @Description 红皮云-企业发展分析-存续企业统计
* @Author wuyilong
* @Date 2024-07-04
*/
@DS("star_rock")
@Mapper
public interface AdsInduEtpEnterpriseNumDfMapper extends BaseMapper<AdsInduEtpEnterpriseNumDf> {

}
