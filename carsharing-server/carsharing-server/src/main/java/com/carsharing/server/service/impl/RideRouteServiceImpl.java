package com.carsharing.server.service.impl;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.RideRouteMapper;
import com.carsharing.server.service.BaseService;
import com.carsharing.server.service.RideRouteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hucl on 2017/7/18.
 */
@Transactional
@Service
public class RideRouteServiceImpl extends BaseServiceImpl<RideRoute> implements RideRouteService {

    @Resource
    private RideRouteMapper rideRouteMapper;

    @Override
    public List<RideRoute> getRideRoutesByUserNo(String userNo) {
        return null;
    }

    @Override
    public List<RideDemand> getRideRouteByDemand(RideDemand demand) {
        return null;
    }

    @Override
    protected BaseDao<RideRoute> getMapper() {
        return rideRouteMapper;
    }
}
