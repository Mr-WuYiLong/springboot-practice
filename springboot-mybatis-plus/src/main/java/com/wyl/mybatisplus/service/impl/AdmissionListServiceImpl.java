package com.wyl.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyl.mybatisplus.common.CommonPage;
import com.wyl.mybatisplus.dto.AdmissionListDto;
import com.wyl.mybatisplus.entity.AdmissionList;
import com.wyl.mybatisplus.mapper.AdmissionListMapper;
import com.wyl.mybatisplus.req.AdmissionListReq;
import com.wyl.mybatisplus.service.AdmissionListService;
import com.wyl.mybatisplus.vo.AdmissionListVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
* @Description 录取名单
* @Author WuYiLong
* @Date 2025-03-16
*/
@Service
public class AdmissionListServiceImpl extends ServiceImpl<AdmissionListMapper, AdmissionList> implements AdmissionListService {

    @Override
    public CommonPage<AdmissionListVo> page(AdmissionListReq admissionListReq) {
        Page<AdmissionList> page = new Page<>(admissionListReq.getCurrentPage(), admissionListReq.getPageSize());
        LambdaQueryWrapper<AdmissionList> wrapper = Wrappers.lambdaQuery(AdmissionList.class);
        wrapper.like(StrUtil.isNotBlank(admissionListReq.getUniversity()), AdmissionList::getUniversity, admissionListReq.getUniversity());
        wrapper.eq(admissionListReq.getYear() != null, AdmissionList::getYear, admissionListReq.getYear());
        wrapper.orderByDesc(AdmissionList::getCreateTime);
        Page<AdmissionList> admissionListPage = baseMapper.selectPage(page, wrapper);
        List<AdmissionListVo> admissionListVos = admissionListPage.getRecords().stream().map(m -> {
            AdmissionListVo admissionListVo = new AdmissionListVo();
            BeanUtil.copyProperties(m, admissionListVo);
            return admissionListVo;
        }).collect(Collectors.toList());
        CommonPage<AdmissionListVo> commonPage = new CommonPage<>();
        commonPage.setCurrentPage(admissionListReq.getCurrentPage());
        commonPage.setPageSize(admissionListReq.getPageSize());
        commonPage.setTotal(admissionListPage.getTotal());
        commonPage.setList(admissionListVos);
        return commonPage;
    }

    @Override
    public void save(AdmissionListDto admissionListDto) {
        if(admissionListDto.getId() != null){
            AdmissionList admissionList = baseMapper.selectById(admissionListDto.getId());
            CopyOptions copyOptions = new CopyOptions().setIgnoreNullValue(true);
            BeanUtil.copyProperties(admissionListDto, admissionList,copyOptions);
            admissionList.setUpdateTime(LocalDateTime.now());
            admissionList.updateById();
        }else{
            AdmissionList admissionList = new AdmissionList();
            BeanUtil.copyProperties(admissionListDto, admissionList);
            admissionList.setCreateTime(LocalDateTime.now());
            admissionList.setUpdateTime(LocalDateTime.now());
            admissionList.insert();
        }
    }

    @Override
    public AdmissionListVo getDetail(Long id) {
        AdmissionList admissionList = baseMapper.selectById(id);
        AdmissionListVo admissionListVo = BeanUtil.copyProperties(admissionList, AdmissionListVo.class);
        return admissionListVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }
}
