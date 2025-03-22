package com.wyl.mybatis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyl.mybatis.common.CommonPage;
import com.wyl.mybatis.dto.AdmissionListDto;
import com.wyl.mybatis.entity.AdmissionList;
import com.wyl.mybatis.exception.BusinessException;
import com.wyl.mybatis.mapper.AdmissionListMapper;
import com.wyl.mybatis.req.AdmissionListReq;
import com.wyl.mybatis.service.AdmissionListService;
import com.wyl.mybatis.vo.AdmissionListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2025/3/16 16:59
 */
@Service
public class AdmissionListServiceImpl implements AdmissionListService {

    @Autowired
    private AdmissionListMapper admissionListMapper;

    @Override
    public CommonPage<AdmissionListVo> page(AdmissionListReq req) {
        Page page = PageHelper.startPage(req.getCurrentPage(), req.getPageSize());
        List<AdmissionList> admissionLists = admissionListMapper.selectPage(req);

        List<AdmissionListVo> admissionListVos = admissionLists.stream().map(m -> {
            AdmissionListVo admissionListVo = new AdmissionListVo();
            BeanUtil.copyProperties(m, admissionListVo);
            return admissionListVo;
        }).collect(Collectors.toList());

        CommonPage<AdmissionListVo> commonPage = new CommonPage<>();
        commonPage.setCurrentPage(req.getCurrentPage());
        commonPage.setPageSize(req.getPageSize());
        commonPage.setList(admissionListVos);
        commonPage.setTotal(page.getTotal());
        return commonPage;
    }

    @Override
    public AdmissionListVo getById(Long id) {
        AdmissionList admissionList = admissionListMapper.selectById(id);
        AdmissionListVo admissionListVo = BeanUtil.copyProperties(admissionList, AdmissionListVo.class);
        return admissionListVo;
    }


    @Override
    public void delete(List<Long> idList) {
        admissionListMapper.deleteByIds(idList);
    }

    @Override
    public Long save(AdmissionListDto admissionListDto) {

        if(admissionListDto.getId() != null){
            AdmissionList admissionList = admissionListMapper.selectById(admissionListDto.getId());
            if(admissionList == null){
                throw new BusinessException("数据不存在");
            }
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setIgnoreNullValue(true);
            BeanUtil.copyProperties(admissionListDto, admissionList,copyOptions);
            admissionList.setUpdateTime(new Date());
            admissionListMapper.updateById(admissionList);
            return admissionList.getId();
        }else{
            AdmissionList admissionList = new AdmissionList();
            beforeSave(admissionList, admissionListDto);
            admissionListMapper.insert(admissionList);
            return admissionList.getId();
        }
    }

    public void beforeSave(AdmissionList entity,AdmissionListDto admissionListDto) {
        BeanUtil.copyProperties(admissionListDto, entity);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setDeleted(0);
        entity.setId(IdUtil.getSnowflakeNextId());
    }
}