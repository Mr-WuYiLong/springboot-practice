package com.wyl.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.mybatisplus.common.CommonPage;
import com.wyl.mybatisplus.dto.AdmissionListDto;
import com.wyl.mybatisplus.entity.AdmissionList;
import com.wyl.mybatisplus.req.AdmissionListReq;
import com.wyl.mybatisplus.vo.AdmissionListVo;

import java.util.List;

/**
* @Description 录取名单
* @Author WuYiLong
* @Date 2025-03-16
*/
public interface AdmissionListService extends IService<AdmissionList> {

    /**
     * 分页
     * @param admissionListReq
     * @return
     */
    CommonPage<AdmissionListVo> page(AdmissionListReq admissionListReq);

    /**
     * 保存
     * @param admissionListDto
     */
    void save(AdmissionListDto admissionListDto);

    /**
     * 详情
     * @param id
     * @return
     */
    AdmissionListVo getDetail(Long id);

    /**
     * 删除
     * @param ids
     */
    void delete(List<Long> ids);
}
