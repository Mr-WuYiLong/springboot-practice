package com.wyl.redis.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyl.redis.bean.DictionaryBean;
import com.wyl.redis.constant.DictionaryConst;
import com.wyl.redis.entity.SimpleDictionary;
import com.wyl.redis.exception.BusinessException;
import com.wyl.redis.service.DictionaryOperate;
import com.wyl.redis.service.SimpleDictionaryService;
import com.wyl.redis.vo.SimpleDictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/11 16:21
 */
@Service
public class SimpleDictionaryOperate implements DictionaryOperate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SimpleDictionaryService simpleDictionaryService;

    private String getCode(String key) {
        int num = StrUtil.lastIndexOfIgnoreCase(key, ":");
        return key.substring(num+1);
    }
    @Override
    public List list(String key) {
        if(!redisTemplate.hasKey(key)) {
            List<SimpleDictionaryVo> simpleDictionaryVos = simpleDictionaryService.listByCodeNotParent(getCode(key));
            if(CollUtil.isEmpty(simpleDictionaryVos)) {
               throw new BusinessException("字典的key不存在");
            }
            List<DictionaryBean> dictionaryBeans = simpleDictionaryVos.stream().map(m -> {
                DictionaryBean dictionaryBean = new DictionaryBean();
                dictionaryBean.setName(m.getName());
                dictionaryBean.setParentCode(String.valueOf(m.getParentId()));
                dictionaryBean.setCode(m.getCode());
                return dictionaryBean;
            }).collect(Collectors.toList());
            redisTemplate.opsForValue().set(key, dictionaryBeans);
            return dictionaryBeans;
        }
        List<DictionaryBean> list = (List<DictionaryBean>)redisTemplate.opsForValue().get(key);
        return list;
    }

    @Override
    public   List<Tree<Long>> tree(String key) {
        if(!redisTemplate.hasKey(key)) {
            List<SimpleDictionaryVo> simpleDictionaryVos = simpleDictionaryService.listByCode(getCode(key));
            if(CollUtil.isEmpty(simpleDictionaryVos)) {
                throw new BusinessException("字典的key不存在");
            }

            List<Tree<Long>> trees = TreeUtil.build(simpleDictionaryVos, 0L, (t1, t2) -> {
                t2.setId(t1.getId());
                t2.setParentId(t1.getParentId());
                t2.setName(t1.getName());
            });

            if(CollUtil.isNotEmpty(trees.get(0))) {
                redisTemplate.opsForValue().set(key, trees.get(0).getChildren());
            }
            return trees;
        }
        List<Tree<Long>> trees = (List<Tree<Long>>)redisTemplate.opsForValue().get(key);
        return trees;
    }

    @Override
    public String codeNameMap(String key, String code) {
        if(!redisTemplate.opsForHash().hasKey(key,code)) {
            SimpleDictionary simpleDictionary = simpleDictionaryService.getById(code);
            if(simpleDictionary != null) {
                redisTemplate.opsForHash().putIfAbsent(key,simpleDictionary.getId().toString(),simpleDictionary.getName());
                return simpleDictionary.getName();
            }
            return null;
        }
        String name = (String)redisTemplate.opsForHash().get(key, code);
        return name;
    }

    @Override
    public String nameCodeMap(String key, String name) {
        return null;
    }

    @Override
    public String supportType() {
        return DictionaryConst.SIMPLE_DICTIONARY;
    }
}
