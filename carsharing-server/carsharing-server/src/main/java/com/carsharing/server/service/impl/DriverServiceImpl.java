package com.carsharing.server.service.impl;

import com.carsharing.server.entity.Driver;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.DriverMapper;
import com.carsharing.server.service.DriverService;
import com.carsharing.server.util.JsonResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by hucl on 2017/7/12.
 */
@Transactional
@Service
public class DriverServiceImpl extends BaseServiceImpl<Driver> implements DriverService {

    private static Logger logger = Logger.getLogger(DriverServiceImpl.class);
    @Resource
    private DriverMapper driverMapper;

    @Override
    protected BaseDao<Driver> getMapper() {
        return driverMapper;
    }

    @Override
    public JsonResponse<Driver> regToDriver(String mobile) {
        return null;
    }
}
