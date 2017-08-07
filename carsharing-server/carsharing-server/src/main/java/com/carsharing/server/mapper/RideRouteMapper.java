package com.carsharing.server.mapper;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;

import java.util.Date;
import java.util.List;

/**
 * Created by hucl on 2017/7/18.
 */
public interface RideRouteMapper extends BaseDao<RideRoute> {
    public List<RideRoute> getNormalStateRideRoutes();

    public List<RideRoute> getRideRoutesByUserNo(String userNo);

    public List<RideRoute> getRideRoutesByDriverNo(String userNo);

    public List<RideDemand> getRideRouteByDemand(RideDemand demand);

    public Integer deleteOverdueRoutes(Date date);
}
