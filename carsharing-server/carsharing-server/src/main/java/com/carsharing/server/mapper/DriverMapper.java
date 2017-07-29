package com.carsharing.server.mapper;

import com.carsharing.server.entity.Driver;
import com.carsharing.server.entity.User;

import java.util.List;

/**
 * Created by hucl on 2017/7/12.
 */
public interface DriverMapper extends BaseDao<Driver> {

    public List<Driver> selectByUserNo(String userNo);

    public boolean insertService(Driver driver);

    public boolean updateService(Driver driver);
}
