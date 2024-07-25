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

import java.util.List;

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

    @ApiOperation(value = "列表-基于redis的缓存")
    @GetMapping(value = "listFullCityByRedis")
    public ResponseData<List<FullCityVo>> listFullCityByRedis() {
        return ResponseData.successInstance(fullCityService.listFullCityByRedis());
    }

    @ApiOperation(value = "列表-基于caffeine的缓存")
    @GetMapping(value = "listFullCityByCaffeine")
    public ResponseData<List<FullCityVo>> listFullCityByCaffeine() {
        return ResponseData.successInstance(fullCityService.listFullCityByCaffeine());
    }

    @ApiOperation(value = "列表-基于EhCache的缓存")
    @GetMapping(value = "listFullCityByEhCache")
    public ResponseData<List<FullCityVo>> listFullCityByEhCache() {
        return ResponseData.successInstance(fullCityService.listFullCityByEhCache());
    }
}
