package com.wyl.redis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wyl.redis.entity.SimpleDictionary;
import com.wyl.redis.mapper.SimpleDictionaryMapper;
import com.wyl.redis.service.SimpleDictionaryService;
import com.wyl.redis.vo.SimpleDictionaryVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @Description 
* @Author wuyilong
* @Date 2024-07-11
*/
@CacheConfig(cacheManager = "caffeineCacheManager",cacheNames = "cache")
@Service
public class SimpleDictionaryServiceImpl extends ServiceImpl<SimpleDictionaryMapper, SimpleDictionary> implements SimpleDictionaryService {

    @Override
    public  List<SimpleDictionaryVo> listByParentId(Long parentId) {
        LambdaQueryWrapper<SimpleDictionary> wrapper = Wrappers.lambdaQuery(SimpleDictionary.class);
        wrapper.eq(SimpleDictionary::getParentId,parentId);
        List<SimpleDictionary> list = list(wrapper);
        List<SimpleDictionaryVo> simpleDictionaryVos = list.stream().map(m -> {
            SimpleDictionaryVo simpleDictionaryVo = new SimpleDictionaryVo();
            BeanUtil.copyProperties(m, simpleDictionaryVo);
            return simpleDictionaryVo;
        }).collect(Collectors.toList());
        return simpleDictionaryVos;
    }

    @Override
    public List<SimpleDictionaryVo> listByCodeNotParent(String code) {
        LambdaQueryWrapper<SimpleDictionary> wrapper = Wrappers.lambdaQuery(SimpleDictionary.class);
        wrapper.eq(SimpleDictionary::getCode,code);
        wrapper.ne(SimpleDictionary::getParentId,0);
        List<SimpleDictionary> list = list(wrapper);
        List<SimpleDictionaryVo> simpleDictionaryVos = list.stream().map(m -> {
            SimpleDictionaryVo simpleDictionaryVo = new SimpleDictionaryVo();
            BeanUtil.copyProperties(m, simpleDictionaryVo);
            return simpleDictionaryVo;
        }).collect(Collectors.toList());
        return simpleDictionaryVos;
    }

    @Override
    public List<SimpleDictionaryVo> listParentHasCode() {

        LambdaQueryWrapper<SimpleDictionary> wrapper = Wrappers.lambdaQuery(SimpleDictionary.class);
        wrapper.eq(SimpleDictionary::getParentId,0);
        List<SimpleDictionary> list = list(wrapper);
        List<SimpleDictionaryVo> simpleDictionaryVos = list.stream().map(m -> {
            SimpleDictionaryVo simpleDictionaryVo = new SimpleDictionaryVo();
            BeanUtil.copyProperties(m, simpleDictionaryVo);
            return simpleDictionaryVo;
        }).collect(Collectors.toList());
        return simpleDictionaryVos;
    }

    @Override
    public List<SimpleDictionaryVo> listByCode(String code) {
        LambdaQueryWrapper<SimpleDictionary> wrapper = Wrappers.lambdaQuery(SimpleDictionary.class);
        wrapper.eq(SimpleDictionary::getCode,code);
        List<SimpleDictionary> list = list(wrapper);
        List<SimpleDictionaryVo> simpleDictionaryVos = list.stream().map(m -> {
            SimpleDictionaryVo simpleDictionaryVo = new SimpleDictionaryVo();
            BeanUtil.copyProperties(m, simpleDictionaryVo);
            return simpleDictionaryVo;
        }).collect(Collectors.toList());
        return simpleDictionaryVos;
    }

    @Override
    public SimpleDictionaryVo getCodeByName(String name) {
        LambdaQueryWrapper<SimpleDictionary> wrapper = Wrappers.lambdaQuery(SimpleDictionary.class);
        wrapper.eq(SimpleDictionary::getName,name);
        wrapper.last("limit 1");
        SimpleDictionary simpleDictionary = getOne(wrapper);
        if(simpleDictionary == null) {
            return null;
        }
        SimpleDictionaryVo simpleDictionaryVo = BeanUtil.copyProperties(simpleDictionary, SimpleDictionaryVo.class);
        return simpleDictionaryVo;
    }


    @Cacheable(value = "listSimpleDictionary",key = "'list'",unless = "#result == null")
    @Override
    public List<SimpleDictionaryVo> listSimpleDictionary() {
        List<SimpleDictionary> list = list();
        List<SimpleDictionaryVo> simpleDictionaryVos = list.stream().map(m -> {
            SimpleDictionaryVo simpleDictionaryVo = new SimpleDictionaryVo();
            BeanUtil.copyProperties(m, simpleDictionaryVo);
            return simpleDictionaryVo;
        }).collect(Collectors.toList());
        return simpleDictionaryVos;
    }
}
