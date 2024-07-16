package com.wyl.redis.service.impl;

import ch.qos.logback.core.util.ContentTypeUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.http.ContentType;
import com.wyl.redis.bean.DictionaryBean;
import com.wyl.redis.constant.DictionaryConst;
import com.wyl.redis.entity.SimpleDictionary;
import com.wyl.redis.exception.BusinessException;
import com.wyl.redis.service.DictionaryOperate;
import com.wyl.redis.service.SimpleDictionaryService;
import com.wyl.redis.vo.SimpleDictionaryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author WuYiLong
 * @Date 2024/7/3 17:23
 */
@Slf4j
@Component
public class DictionaryService implements ApplicationContextAware {

    private Map<String,DictionaryOperate>  dictionaryMaps = new HashMap<>();
    public static List<String> dickeys = new ArrayList<>();

    @Autowired
    private SimpleDictionaryService simpleDictionaryService;

    @Autowired
    private RedisTemplate redisTemplate;

    static {
        dickeys.add(DictionaryConst.FULL_CITY);
    }

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

    public void refresh() {
        // redis上的字典key全部删除
        Set keys = redisTemplate.keys("dic*");
        keys.forEach(v->{
            redisTemplate.delete(v);
        });

        //重新刷新字典key
        for (String dickey : dickeys) {
            dictionaryMaps.get(dickey).list(dickey);
            dictionaryMaps.get(dickey).tree(dickey);
        }

        // 列表
        List<SimpleDictionaryVo> simpleDictionaryVos = simpleDictionaryService.listParentHasCode();
        for (SimpleDictionaryVo simpleDictionaryVo : simpleDictionaryVos) {
            String code = simpleDictionaryVo.getCode();
            List<SimpleDictionaryVo> simpleDictionaryVoList = simpleDictionaryService.listByCodeNotParent(code);

            if(CollUtil.isNotEmpty(simpleDictionaryVoList)) {
                List<DictionaryBean> dictionaryBeans = simpleDictionaryVoList.stream().map(m -> {
                    DictionaryBean dictionaryBean = new DictionaryBean();
                    dictionaryBean.setParentCode(String.valueOf(m.getParentId()));
                    dictionaryBean.setCode(m.getId().toString());
                    dictionaryBean.setName(m.getName());
                    return dictionaryBean;
                }).collect(Collectors.toList());
                redisTemplate.opsForValue().set(DictionaryConst.SIMPLE_DICTIONARY_LIST + ":" + code, dictionaryBeans);

                simpleDictionaryVoList.add(simpleDictionaryVo);

                // 构建树
                List<Tree<Long>> build = TreeUtil.build(simpleDictionaryVoList, 0l, (t1, t2) -> {
                    t2.setId(t1.getId());
                    t2.setParentId(t1.getParentId());
                    t2.setName(t1.getName());
                });

                if(CollUtil.isNotEmpty(build.get(0))) {
                    redisTemplate.opsForValue().set(DictionaryConst.SIMPLE_DICTIONARY_TREE+":"+code,build.get(0).getChildren());
                }
            }
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, DictionaryOperate> dictionaryOperateMap = applicationContext.getBeansOfType(DictionaryOperate.class);
        dictionaryOperateMap.forEach((k,v)->{
            dictionaryMaps.put(v.supportType(),v);
        });
    }
}
