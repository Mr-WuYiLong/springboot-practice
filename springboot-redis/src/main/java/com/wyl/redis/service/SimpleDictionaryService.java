package com.wyl.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.redis.entity.SimpleDictionary;
import com.wyl.redis.vo.SimpleDictionaryVo;

import java.util.List;

/**
* @Description 
* @Author wuyilong
* @Date 2024-07-11
*/
public interface SimpleDictionaryService extends IService<SimpleDictionary> {

    /**
     * 根据父级id查询
     * @param parentId
     * @return
     */
    List<SimpleDictionaryVo> listByParentId(Long parentId);

    /**
     * 根据标识码查询,排除顶级id
     * @param code
     * @return
     */
    List<SimpleDictionaryVo> listByCodeNotParent(String code);

    /**
     * 查询带有code的字典
     * @return
     */
    List<SimpleDictionaryVo> listParentHasCode();

    /**
     * 根据code查询
     * @param code
     * @return
     */
    List<SimpleDictionaryVo> listByCode(String code);

    /**
     *
     * @param name
     * @return
     */
    SimpleDictionaryVo getCodeByName(String name);


}
