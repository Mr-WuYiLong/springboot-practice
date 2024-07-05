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
public class FullCityDic implements DictionaryOperate<DictionaryBean> {

    @Autowired
    private FullCityService fullCityService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<DictionaryBean> list() {

        log.info("*****序列化列表*******");
        if(!redisTemplate.hasKey(DictionaryConst.FULL_CITY_LIST)) {
            List<FullCity> list = fullCityService.list();

            List<DictionaryBean> dictionaryBeans = list.stream().map(m -> {
                DictionaryBean dictionaryBean = new DictionaryBean();
                dictionaryBean.setCode(m.getCode());
                dictionaryBean.setName(m.getName());
                dictionaryBean.setLevel(m.getLevel());
                return dictionaryBean;
            }).collect(Collectors.toList());

//            redisTemplate.opsForList().leftPush(DictionaryConst.FULL_CITY_LIST,list);
            redisTemplate.opsForValue().set(DictionaryConst.FULL_CITY_LIST,dictionaryBeans);
        }
//        List list = redisTemplate.opsForList().range(DictionaryConst.FULL_CITY_LIST, 0, -1);
        List<DictionaryBean> list = (List<DictionaryBean>)redisTemplate.opsForValue().get(DictionaryConst.FULL_CITY_LIST);
//        if(CollUtil.isNotEmpty(list)) {
//            Object o = list.get(0);
//            List<FullCity> fullCities = JSONUtil.toList(JSONUtil.toJsonStr(o), FullCity.class);
//            return fullCities;
//        }
        return list;
    }
}
