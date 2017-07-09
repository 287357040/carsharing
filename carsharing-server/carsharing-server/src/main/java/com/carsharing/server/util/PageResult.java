package com.carsharing.server.util;

import com.github.pagehelper.Page;

import java.util.List;


/**
 * 功能概要：
 *
 */
public class PageResult<T> {

    private List<T> dataList;//数据

    private int pageNo;//当前页

    private int pageSize;//条数

    private long total;//总条数

    private int pages;//总页面数目

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    public static <T> PageResult<T> toPageResult(List<T> datas, PageResult<T> result) {
        if (datas instanceof Page) {
            Page<T> page = (Page<T>) datas;
            result.setPageNo(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setDataList(page.getResult());
            result.setTotal(page.getTotal());
            result.setPages(page.getPages());
        } else {
            result.setPageNo(1);
            result.setPageSize(datas.size());
            result.setDataList(datas);
            result.setTotal(datas.size());
        }

        return result;
    }


}
