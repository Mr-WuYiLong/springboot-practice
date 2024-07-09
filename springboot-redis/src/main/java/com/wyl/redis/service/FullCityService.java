package com.wyl.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.redis.entity.FullCity;
import com.wyl.redis.vo.FullCityVo;

/**
* @Description 
* @Author wuyilong
* @Date 2024-07-03
*/
public interface FullCityService extends IService<FullCity> {

    /**
     * 回调父级编码
     */
    void backFillParentCode();

    /**
     * 缓存字符串
     * @return
     */
    String cacheString(String areaCode);

    /**
     * 根据地区编码查询
     * @param code
     * @return
     */
    FullCityVo getFullCity(String code);





}
