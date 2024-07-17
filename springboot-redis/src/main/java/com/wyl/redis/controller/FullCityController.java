package com.wyl.redis.controller;

import com.wyl.common.bean.ResponseData;
import com.wyl.redis.service.FullCityService;
import com.wyl.redis.vo.FullCityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description 
* @Author wuyilong
* @Date 2024-07-03
*/
@Api(tags = "省市区api")
@RestController
@RequestMapping("/fullCity")
public class FullCityController {

    @Autowired
    private FullCityService fullCityService;

    @ApiOperation(value = "回调父级编码")
    @GetMapping(value = "backFillParentCode")
    public ResponseData backFillParentCode() {
        fullCityService.backFillParentCode();
        return ResponseData.success();
    }

    @ApiOperation(value = "根据地区编码查询")
    @GetMapping(value = "getFullCity")
    public ResponseData<FullCityVo> getFullCity(String code) {
        return ResponseData.successInstance(fullCityService.getByCode(code));
    }
}
