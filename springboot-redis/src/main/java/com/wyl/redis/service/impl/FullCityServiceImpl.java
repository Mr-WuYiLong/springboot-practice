package com.wyl.redis.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyl.redis.entity.FullCity;
import com.wyl.redis.mapper.FullCityMapper;
import com.wyl.redis.service.FullCityService;
import com.wyl.redis.vo.FullCityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @Description 
* @Author wuyilong
* @Date 2024-07-03
*/
@Service
public class FullCityServiceImpl extends ServiceImpl<FullCityMapper, FullCity> implements FullCityService {

    private static final Logger log = LoggerFactory.getLogger(FullCityServiceImpl.class);

    @Override
    public void backFillParentCode() {
        List<FullCity> list = list();
        list.parallelStream().forEach(v->{
            v.setParentCode(interceptCode(v));
            v.updateById();
        });
        log.info("******父级编码赋值完毕*******");
    }

    private String interceptCode(FullCity fullCity) {
        if(fullCity.getLevel() == 1) {
            return "0";
        }else if(fullCity.getLevel() == 2) {
            return fullCity.getCode().substring(0,2);
        }else if(fullCity.getLevel() == 3) {
            return fullCity.getCode().substring(0,4);
        }else if(fullCity.getLevel() == 4) {
            return fullCity.getCode().substring(0,6);
        }
        return null;
    }

    @Override
    public FullCityVo getByCode(String code) {
        LambdaQueryWrapper<FullCity> wrapper = Wrappers.lambdaQuery(FullCity.class);
        wrapper.eq(FullCity::getCode,code);
        FullCity fullCity = getOne(wrapper);
        if(fullCity == null) {
            return null;
        }
        FullCityVo fullCityVo = BeanUtil.copyProperties(fullCity, FullCityVo.class);
        return fullCityVo;
    }

    @Override
    public FullCityVo getByFullName(String fullName) {
        LambdaQueryWrapper<FullCity> wrapper = Wrappers.lambdaQuery(FullCity.class);
        wrapper.eq(FullCity::getFullName,fullName);
        FullCity fullCity = getOne(wrapper);
        if(fullCity == null) {
            return null;
        }
        FullCityVo fullCityVo = new FullCityVo();
        BeanUtil.copyProperties(fullCity,fullCityVo);
        return fullCityVo;
    }

    @Cacheable(value = "listFullCity",key = "'list'",unless = "#result == null ",cacheManager = "redisCacheManager")
    @Override
    public List<FullCityVo> listFullCity() {
        List<FullCity> fullCities = list();
        List<FullCityVo> fullCityVos = fullCities.stream().map(m -> {
            FullCityVo fullCityVo = new FullCityVo();
            BeanUtil.copyProperties(m, fullCityVo);
            return fullCityVo;
        }).collect(Collectors.toList());
        return fullCityVos;
    }
}
