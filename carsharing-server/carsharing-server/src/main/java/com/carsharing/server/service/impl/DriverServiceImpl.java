package com.carsharing.server.service.impl;

import com.carsharing.server.entity.Driver;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.DriverMapper;
import com.carsharing.server.mapper.UserMapper;
import com.carsharing.server.service.DriverService;
import com.carsharing.server.util.JsonResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public void insertService(Driver driver) {
         driverMapper.insertService(driver);
    }

    @Override
    public void updateService(Driver driver) {
        driverMapper.updateService(driver);
    }


    public DriverMapper getDriverMapper() {
        return driverMapper;
    }

    public void setDriverMapper(DriverMapper driverMapper) {
        this.driverMapper = driverMapper;
    }

}
