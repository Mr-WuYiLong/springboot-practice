package com.wyl.redis.controller;

import com.wyl.common.bean.ResponseData;
import com.wyl.redis.bean.DictionaryBean;
import com.wyl.redis.service.impl.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
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
    public ResponseData refresh() {
        dictionaryService.refresh();
        return ResponseData.success();
    }

    @ApiOperation(value = "字典列表")
    @GetMapping(value = "list")
    public ResponseData list(String key) {
        return ResponseData.successInstance(dictionaryService.list(key));
    }

    @ApiOperation(value = "字典树")
    @GetMapping(value = "tree")
    public ResponseData tree(String key) {
        return ResponseData.successInstance(dictionaryService.tree(key));
    }

    @ApiOperation(value = "根据code获取名称")
    @GetMapping(value = "codeNameMap")
    public ResponseData codeNameMap(String key, String code) {
        return ResponseData.successInstance(dictionaryService.codeNameMap(key,code));
    }

    @ApiOperation(value = "根据名称获取code")
    @GetMapping(value = "nameCodeMap")
    public ResponseData nameCodeMap(String key, String name) {
        return ResponseData.successInstance(dictionaryService.nameCodeMap(key, name));
    }

    @ApiOperation(value = "根据code获取实体")
    @GetMapping(value = "getByCode")
    public ResponseData<DictionaryBean> getByCode(String key, String code) {
        return ResponseData.successInstance(dictionaryService.getByCode(key,code));
    }
}
