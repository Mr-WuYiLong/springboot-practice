package com.wyl.mybatis.controller;

import com.wyl.mybatis.common.CommonPage;
import com.wyl.mybatis.common.ResponseData;
import com.wyl.mybatis.dto.AdmissionListDto;
import com.wyl.mybatis.req.AdmissionListReq;
import com.wyl.mybatis.service.AdmissionListService;
import com.wyl.mybatis.vo.AdmissionListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2025/3/16 17:00
 */
@Api(tags = "院校录取名单")
@RestController
@RequestMapping(value = "admissionList")
public class AdmissionListController {

    @Autowired
    private  AdmissionListService admissionListService;

    @ApiOperation(value = "分页")
    @PostMapping(value = "page")
    public ResponseData<CommonPage<AdmissionListVo>> page(@RequestBody AdmissionListReq req) {
        return ResponseData.successInstance(admissionListService.page(req));
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = "getById")
    public ResponseData<AdmissionListVo> getById(Long id) {
        return ResponseData.successInstance(admissionListService.getById(id));
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "delete")
    public ResponseData delete(@RequestBody List<Long> idList) {
        admissionListService.delete(idList);
        return ResponseData.success();
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "save")
    public ResponseData<Long> save(@RequestBody AdmissionListDto admissionListDto) {
        return ResponseData.successInstance(admissionListService.save(admissionListDto));
    }
}