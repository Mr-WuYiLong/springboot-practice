package com.wyl.redis.controller;

import com.wyl.redis.service.AdsInduEtpEnterpriseStartNumDfNewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description 红皮云-企业发展分析-注册总量增长趋势分析
* @Author wuyilong
* @Date 2024-07-04
*/
@Api(tags = "整合")
@RestController
@RequestMapping("/adsInduEtpEnterpriseStartNumDfNew")
public class AdsInduEtpEnterpriseStartNumDfNewController {

    @Autowired
    private AdsInduEtpEnterpriseStartNumDfNewService adsInduEtpEnterpriseStartNumDfNewService;

    @ApiOperation(value = "三表合一")
    @GetMapping(value = "mergeAdsInduEtpEnterpriseStartNumDfNew")
    public void mergeAdsInduEtpEnterpriseStartNumDfNew() {
        adsInduEtpEnterpriseStartNumDfNewService.mergeAdsInduEtpEnterpriseStartNumDfNew();
    }

    @ApiOperation(value = "2024年存续")
    @GetMapping(value = "adsInduEtpEnterpriseStartNumDfNew2024")
    public void adsInduEtpEnterpriseStartNumDfNew2024() {
        adsInduEtpEnterpriseStartNumDfNewService.adsInduEtpEnterpriseStartNumDfNew2024();
    }
}
