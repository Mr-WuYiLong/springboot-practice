package com.wyl.springbootmybatis.utils;

import java.util.List;

/**
 * @Description 分页结果集
 * @Author YiLong Wu
 * @Date 2020/8/25 下午5:13
 * @Version 1.0.0
 */
public class PageResult {
    private int pageNum;
    private int pageSize;
    private int total;
    private List<?> content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
