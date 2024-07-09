package com.wyl.redis.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wyl.redis.bean.DictionaryBean;
import com.wyl.redis.constant.DictionaryConst;
import com.wyl.redis.service.DictionaryOperate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    static {
        dickeys.add(DictionaryConst.FULL_CITY);
    }

    public List<DictionaryBean> list(String key) {
        List list = dictionaryMaps.get(key).list(DictionaryConst.FULL_CITY_LIST);
        return list;
    }

    public void refresh(String key) {
        String forceKey = "forceKey";
        if(StrUtil.isNotBlank(key)) {
            dictionaryMaps.get(key).list(forceKey);
        }else{
            for (String dickey : dickeys) {
                dictionaryMaps.get(dickey).list(forceKey);
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
