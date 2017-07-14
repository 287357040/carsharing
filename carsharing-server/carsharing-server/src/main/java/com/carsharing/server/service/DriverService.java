package com.carsharing.server.service;

import com.carsharing.server.entity.Driver;
import com.carsharing.server.entity.User;
import com.carsharing.server.util.JsonResponse;

/**
 * Created by hucl on 2017/7/12.
 */
public interface DriverService extends BaseService<Driver> {

    public JsonResponse<Driver> regToDriver(String mobile);
}
