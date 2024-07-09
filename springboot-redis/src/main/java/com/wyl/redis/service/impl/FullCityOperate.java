package com.wyl.redis.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.wyl.redis.bean.DictionaryBean;
import com.wyl.redis.constant.DictionaryConst;
import com.wyl.redis.entity.FullCity;
import com.wyl.redis.service.DictionaryOperate;
import com.wyl.redis.service.FullCityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:36
 */
@Slf4j
@Service
public class FullCityOperate implements DictionaryOperate<DictionaryBean> {

    @Autowired
    private FullCityService fullCityService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<DictionaryBean> list(String key) {
        if(!redisTemplate.hasKey(key)) {
            List<FullCity> list = fullCityService.list();
            List<DictionaryBean> dictionaryBeans = list.stream().map(m -> {
                DictionaryBean dictionaryBean = new DictionaryBean();
                dictionaryBean.setCode(m.getCode());
                dictionaryBean.setName(m.getName());
                dictionaryBean.setLevel(m.getLevel());
                dictionaryBean.setParentCode(m.getParentCode());
                return dictionaryBean;
            }).collect(Collectors.toList());
            redisTemplate.opsForValue().set(DictionaryConst.FULL_CITY_LIST,dictionaryBeans);
        }
        List<DictionaryBean> list = (List<DictionaryBean>)redisTemplate.opsForValue().get(DictionaryConst.FULL_CITY_LIST);
        return list;
    }

    @Override
    public String supportType() {
        return DictionaryConst.FULL_CITY;
    }
}
