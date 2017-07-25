package com.carsharing.server.service;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;

import java.util.List;

/**
 * Created by hucl on 2017/7/18.
 */
public interface RideDemandService extends BaseService<RideDemand> {
    /**
     * @param userNo 员工号
     * @param state 需求状态
     * @return 需求列表
     */
    public List<RideDemand> getRideDemandsByUserNo(String userNo, int state);

    /**
     *
     * @param routeId 路线
     * @return
     */
    public List<RideDemand> getRideDemandsByRouteId(int routeId);

}
