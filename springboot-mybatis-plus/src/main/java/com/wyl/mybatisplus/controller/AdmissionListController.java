package com.wyl.mybatisplus.controller;

import com.wyl.mybatisplus.common.CommonPage;
import com.wyl.mybatisplus.common.ResponseData;
import com.wyl.mybatisplus.dto.AdmissionListDto;
import com.wyl.mybatisplus.req.AdmissionListReq;
import com.wyl.mybatisplus.service.AdmissionListService;
import com.wyl.mybatisplus.vo.AdmissionListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description 录取名单
* @Author WuYiLong
* @Date 2025-03-16
*/
@Api(tags = "录取名单")
@RestController
@RequestMapping("admissionList")
public class AdmissionListController {

    @Autowired
    private AdmissionListService admissionListService;

    @ApiOperation(value = "分页")
    @PostMapping(value = "page")
    public ResponseData<CommonPage<AdmissionListVo>> page(@RequestBody AdmissionListReq admissionListReq) {
        return ResponseData.successInstance(admissionListService.page(admissionListReq));
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = "save")
    public ResponseData save(@RequestBody AdmissionListDto admissionListDto) {
        admissionListService.save(admissionListDto);
        return ResponseData.success();
    }

    @ApiOperation(value = "获取详情")
    @GetMapping(value = "getDetail")
    public ResponseData<AdmissionListVo> getDetail(Long id) {
        return ResponseData.successInstance(admissionListService.getDetail(id));
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "delete")
    public ResponseData delete(@RequestBody List<Long> ids) {
        admissionListService.delete(ids);
        return ResponseData.success();
    }

}
