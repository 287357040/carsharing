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
     * @param userNo 乘客员工号
     * @return 路线路由列表
     */
    public List<RideRoute> getRideRoutesByUserNo(String userNo);

    /**
     *
     * @param userNo 司机员工号
     * @return 路线路由列表
     */
    public List<RideRoute> getRideRoutesByDriverNo(String No);
    /**
     * @param demand 乘车需求
     * @return
     */
    public List<RideDemand> getRideRouteByDemand(RideDemand demand);

    /**
     * 获取发布中的路线集合
     * @return 路线集合
     */
    public List<RideRoute> getNormalStateRideRoutes();
}
