package com.carsharing.server.service;

import com.carsharing.server.entity.Driver;
import com.carsharing.server.entity.User;
import com.carsharing.server.util.JsonResponse;

import java.util.List;

/**
 * Created by hucl on 2017/7/12.
 */
public interface DriverService extends BaseService<Driver> {

    public void insertService(Driver driver);
    public void updateService(Driver driver);

    public List<Driver> selectByUserNo(String userNo);
}
