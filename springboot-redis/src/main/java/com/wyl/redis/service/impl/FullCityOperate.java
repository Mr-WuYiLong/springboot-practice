package com.wyl.redis.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.wyl.redis.bean.DictionaryBean;
import com.wyl.redis.constant.DictionaryConst;
import com.wyl.redis.entity.FullCity;
import com.wyl.redis.service.DictionaryOperate;
import com.wyl.redis.service.FullCityService;
import com.wyl.redis.vo.FullCityVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:36
 */
@Slf4j
@Service
public class FullCityOperate implements DictionaryOperate {

    @Autowired
    private FullCityService fullCityService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List list(String key) {
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
            redisTemplate.opsForValue().set(key,dictionaryBeans);
            return dictionaryBeans;
        }
        List<DictionaryBean> list = (List<DictionaryBean>)redisTemplate.opsForValue().get(key);
        return list;
    }

    @Override
    public List<Tree<String>> tree(String key) {
        if(!redisTemplate.hasKey(key)) {
            List<FullCity> list = fullCityService.list();
            List<Tree<String>> build = TreeUtil.build(list, "0", (t1, t2) -> {
                t2.setId(t1.getCode());
                t2.setName(t1.getName());
                t2.setParentId(t1.getParentCode());
            });
            redisTemplate.opsForValue().set(key,build);
            return build;
        }
        List<Tree<String>> trees = (List<Tree<String>>)redisTemplate.opsForValue().get(key);
        return trees;
    }

    @Override
    public String codeNameMap(String key, String code) {
        if(!redisTemplate.opsForHash().hasKey(key,code)) {
            FullCityVo fullCityVo = fullCityService.getByCode(code);
            if(fullCityVo != null) {
                redisTemplate.opsForHash().putIfAbsent(key,fullCityVo.getCode(),fullCityVo.getName());
                return fullCityVo.getName();
            }
            return null;
        }
        String name = (String)redisTemplate.opsForHash().get(key, code);
        return name;
    }

    @Override
    public String nameCodeMap(String key, String name) {
        if(!redisTemplate.opsForHash().hasKey(key,name)) {
            FullCityVo fullCityVo = fullCityService.getByFullName(name);
            if(fullCityVo != null) {
                redisTemplate.opsForHash().putIfAbsent(key,fullCityVo.getFullName(),fullCityVo.getCode());
                return fullCityVo.getCode();
            }
            return null;
        }
        String code = (String)redisTemplate.opsForHash().get(key, name);
        return code;
    }

    @Override
    public String supportType() {
        return DictionaryConst.FULL_CITY;
    }
}
