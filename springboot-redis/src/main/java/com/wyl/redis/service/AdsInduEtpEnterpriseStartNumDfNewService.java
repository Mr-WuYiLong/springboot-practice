package com.wyl.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.redis.entity.AdsInduEtpEnterpriseStartNumDfNew;

/**
* @Description 红皮云-企业发展分析-注册总量增长趋势分析
* @Author wuyilong
* @Date 2024-07-04
*/
public interface AdsInduEtpEnterpriseStartNumDfNewService extends IService<AdsInduEtpEnterpriseStartNumDfNew> {

    /**
     * 三表合一
     */
    void mergeAdsInduEtpEnterpriseStartNumDfNew();

    /**
     * 2024存续
     */
    void adsInduEtpEnterpriseStartNumDfNew2024();

}
