package com.carsharing.server.service;

import com.carsharing.server.mapper.BaseDao;


/**
 * 通用Service接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 */
public interface BaseService<T> extends BaseDao<T> {

}
