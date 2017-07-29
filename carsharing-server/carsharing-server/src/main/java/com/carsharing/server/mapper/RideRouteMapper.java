package com.carsharing.server.mapper;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;

import java.util.List;

/**
 * Created by hucl on 2017/7/18.
 */
public interface RideRouteMapper extends BaseDao<RideRoute> {
    public List<RideRoute> getNormalStateRideRoutes();
}
