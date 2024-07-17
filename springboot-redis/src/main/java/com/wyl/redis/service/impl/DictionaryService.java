package com.wyl.redis.service.impl;

import cn.hutool.core.lang.tree.Tree;
import com.wyl.redis.constant.DictionaryConst;
import com.wyl.redis.exception.BusinessException;
import com.wyl.redis.service.DictionaryOperate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:23
 */
@Slf4j
@Component
public class DictionaryService implements ApplicationContextAware {

    private Map<String,DictionaryOperate>  dictionaryMaps = new HashMap<>();

    @Autowired
    private RedisTemplate redisTemplate;

    public DictionaryOperate buildDictionaryOperate(String key) {
        DictionaryOperate dictionaryOperate = dictionaryMaps.get(key);
        if(dictionaryOperate == null) {
            throw new BusinessException("字典的key不存在");
        }
        return dictionaryOperate;
    }

    public List list(String key) {
        String listKey = DictionaryConst.DIC+key+DictionaryConst.LIST;
        if(key.contains(":")) {
            String[] split = key.split(":");
            key = split[0];
            listKey = DictionaryConst.DIC+key+DictionaryConst.LIST+":"+split[1];
        }
        List list = buildDictionaryOperate(key).list(listKey);
        return list;
    }

    public List<Tree<String>> tree(String key) {
        String listKey = DictionaryConst.DIC+key+DictionaryConst.TREE;
        if(key.contains(":")) {
            String[] split = key.split(":");
            key = split[0];
            listKey = DictionaryConst.DIC+key+DictionaryConst.TREE+":"+split[1];
        }
        List<Tree<String>> tree =buildDictionaryOperate(key).tree(listKey);
        return tree;
    }

    public String codeNameMap(String key, String code) {
        String name = buildDictionaryOperate(key).codeNameMap(DictionaryConst.DIC+key+":codeNameMap", code);
        return name;
    }

    public String nameCodeMap(String key, String name) {
        String code = buildDictionaryOperate(key).nameCodeMap(DictionaryConst.DIC+key+":nameCodeMap", name);
        return code;
    }

    public void refresh() {
        Set keys = redisTemplate.keys("dic*");
        keys.forEach(v->{
            redisTemplate.delete(v);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, DictionaryOperate> dictionaryOperateMap = applicationContext.getBeansOfType(DictionaryOperate.class);
        dictionaryOperateMap.forEach((k,v)->{
            dictionaryMaps.put(v.supportType(),v);
        });
    }
}
