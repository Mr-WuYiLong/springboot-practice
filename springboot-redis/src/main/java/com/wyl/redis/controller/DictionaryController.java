package com.wyl.redis.controller;

import com.wyl.redis.bean.DictionaryBean;
import com.wyl.redis.common.ResponseData;
import com.wyl.redis.service.DictionaryOperate;
import com.wyl.redis.service.impl.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/8 10:21
 */
@Api(tags = "字典api")
@RestController
@RequestMapping(value = "dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation(value = "字典刷新")
    @GetMapping(value = "refresh")
    public ResponseData refresh(@RequestParam(required = false) String key) {
        dictionaryService.refresh(key);
        return ResponseData.success();
    }

    @ApiOperation(value = "字典列表")
    @GetMapping(value = "list")
    public ResponseData<List<DictionaryBean>> list(String key) {
        return ResponseData.successInstance(dictionaryService.list(key));
    }
}
