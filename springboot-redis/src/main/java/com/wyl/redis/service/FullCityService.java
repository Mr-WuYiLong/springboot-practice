package com.wyl.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.redis.entity.FullCity;
import com.wyl.redis.vo.FullCityVo;

import java.util.List;

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
     * 根据地区编码查询
     * @param code
     * @return
     */
    FullCityVo getByCode(String code);

    /**
     * 根据全名称查询
     * @param fullName
     * @return
     */
    FullCityVo getByFullName(String fullName);

    /**
     * 列表
     * @return
     */
    List<FullCityVo> listFullCity();





}
