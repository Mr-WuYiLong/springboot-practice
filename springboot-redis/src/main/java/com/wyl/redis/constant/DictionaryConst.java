package com.wyl.redis.constant;

/**
 * @Description 字典常量
 * @Author WuYiLong
 * @Date 2024/7/4 9:16
 */
public class DictionaryConst {

    /**字典模块**/
    public static final String DIC="dic:";
    /**列表结构**/
    public static final String LIST = "-list";
    /**树结构**/
    public static final String TREE = "-tree";
    /**key-value结构**/
    public static final String MAP = "-map";

    /**省市区**/
    public static final String FULL_CITY = "full-city";

    public static final String SIMPLE_DICTIONARY = "simple-dictionary";

    /**省市区-列表**/
    public static final String FULL_CITY_LIST = DIC+FULL_CITY+LIST;
    /**省市区-树**/
    public static final String FULL_CITY_TREE = DIC+FULL_CITY+TREE;

    /**普通字典-列表**/
    public static final String SIMPLE_DICTIONARY_LIST = DIC+SIMPLE_DICTIONARY+LIST;
    /**普通字典-树**/
    public static final String SIMPLE_DICTIONARY_TREE = DIC+SIMPLE_DICTIONARY+TREE;







}
