package com.carsharing.server.mapper;

import com.carsharing.server.util.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 通用DAO接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 */
public interface BaseDao<T> {
    /**
     * 插入数据
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public int insert(T entity) throws Exception;

    /**
     * 更新数据
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public int updateByPrimaryKey(T entity) throws Exception;

    /**
     * 插入数据(选择性地)
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public int insertSelective(T entity) throws Exception;

    /**
     * 更新数据(选择性地)
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public int updateByPrimaryKeySelective(T entity) throws Exception;

    /**
     * 删除数据
     *
     * @param id
     * @throws Exception
     * @throws
     */
    public int deleteByPrimaryKey(int id) throws Exception;

    /**
     * 删除数据
     *
     * @param entity
     * @throws Exception
     * @throws
     */
    public int delete(T entity) throws Exception;

    /**
     * 根据id查询单个记录
     *
     * @param id
     * @return
     * @throws Exception
     * @throws
     */
    public T selectByPrimaryKey(int id);

    /**
     * 根据对象查询单个记录
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public T getOne(T entity);

    /**
     * 根据对象查询信息
     *
     * @param obj
     * @return
     * @throws Exception
     * @throws
     */
    public Object getObject(Object obj);

    /**
     * 获取记录 分页
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public PageResult<T> queryByPage(@Param("t") PageResult<T> t, @Param("entity") T entity);

    public List<T> getPage(T entity);

    public PageResult<T> queryByPageFront(@Param("t") PageResult<T> t, @Param("entity") T entity);

    public List<T> getPageFront(T entity);

    /**
     * 查询总数
     *
     * @param entity
     * @return
     * @throws Exception
     * @throws
     */
    public int getCount(T entity);

    public List<T> getAll();

    public List<T> getAllBySelect(T entity);

}

