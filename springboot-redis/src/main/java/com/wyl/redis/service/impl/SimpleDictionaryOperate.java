package com.wyl.redis.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
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

    @Override
    public List list(String key) {
        if(!redisTemplate.hasKey(key)) {
            List<SimpleDictionaryVo> simpleDictionaryVos = simpleDictionaryService.listByCode(key);
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
            redisTemplate.opsForValue().set(DictionaryConst.DIC+DictionaryConst.SIMPLE_DICTIONARY+":"+key, dictionaryBeans);
        }
        List<DictionaryBean> list = (List<DictionaryBean>)redisTemplate.opsForValue().get(key);
        return list;
    }

    @Override
    public List<Tree<String>> tree(String key) {
        return null;
    }

    @Override
    public String supportType() {
        return DictionaryConst.SIMPLE_DICTIONARY;
    }
}
