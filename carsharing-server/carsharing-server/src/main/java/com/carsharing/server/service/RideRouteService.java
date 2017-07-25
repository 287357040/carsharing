package com.carsharing.server.service;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;

import java.util.List;

/**
 * Created by hucl on 2017/7/18.
 */
public interface RideRouteService extends BaseService<RideRoute> {

    /**
     *
     * @param userNo 员工号
     * @return 路线路由列表
     */
    public List<RideRoute> getRideRoutesByUserNo(String userNo);

    /**
     * @param demand 乘车需求
     * @return
     */
    public List<RideDemand> getRideRouteByDemand(RideDemand demand);
}
