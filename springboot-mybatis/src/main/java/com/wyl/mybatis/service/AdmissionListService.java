package com.wyl.mybatis.service;

import com.wyl.mybatis.common.CommonPage;
import com.wyl.mybatis.dto.AdmissionListDto;
import com.wyl.mybatis.req.AdmissionListReq;
import com.wyl.mybatis.vo.AdmissionListVo;

import java.util.List;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2025/3/16 16:58
 */
public interface AdmissionListService {

    /**
     * 分页
     * @param req
     * @return
     */
    CommonPage<AdmissionListVo> page(AdmissionListReq req);

    /**
     * 根据id获取详情
     * @param id
     * @return
     */
    AdmissionListVo getById(Long id);

    /**
     * 删除
     * @param idList
     */
    void delete(List<Long> idList);

    /**
     * 保存
     * @param admissionListDto
     * @return
     */
    Long save(AdmissionListDto admissionListDto);
}