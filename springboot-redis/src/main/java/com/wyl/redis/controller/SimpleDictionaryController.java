package com.wyl.redis.controller;

import com.wyl.common.bean.ResponseData;
import com.wyl.redis.service.SimpleDictionaryService;
import com.wyl.redis.vo.SimpleDictionaryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/23 16:48
 */
@Api(tags = "普通字典")
@RestController
@RequestMapping(value = "simpleDictionary")
public class SimpleDictionaryController {

    @Autowired
    public SimpleDictionaryService simpleDictionaryService;

    @ApiOperation(value = "列表")
    @GetMapping(value = "listSimpleDictionary")
    public ResponseData<List<SimpleDictionaryVo>> listSimpleDictionary() {
        return ResponseData.successInstance(simpleDictionaryService.listSimpleDictionary());
    }
}
