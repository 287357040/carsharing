package com.carsharing.server.service.impl;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.mapper.BaseDao;
import com.carsharing.server.mapper.RideDemandMapper;
import com.carsharing.server.service.BaseService;
import com.carsharing.server.service.RideDemandService;
import com.carsharing.server.util.PageResult;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hucl on 2017/7/18.
 */
@Transactional
@Service
public class RideDemandServiceImpl extends BaseServiceImpl<RideDemand> implements RideDemandService {

    private static Logger logger = Logger.getLogger(DriverServiceImpl.class);

    @Resource
    private RideDemandMapper rideDemandMapper;

    @Override
    protected BaseDao<RideDemand> getMapper() {
        return rideDemandMapper;
    }

    @Override
    public List<RideDemand> getRideDemandsByUserNo(String userNo, int state) {
        return rideDemandMapper.getRideDemandsByUserNo(userNo,state);
    }

    @Override
    public List<RideDemand> getRideDemandsByRouteId(int routeId) {

        return rideDemandMapper.getRideDemandsByRouteId(routeId);
    }

    @Override
    public List<RideDemand> getRideDemandsByState(int state) {
        return rideDemandMapper.getRideDemandsByState(state);
    }

    @Override
    public RideDemand getDemandByUserNoAndRouteId(String userNo, Integer routeId) {
        return rideDemandMapper.getDemandByUserNoAndRouteId(userNo,routeId);
    }
}
