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

    /**
     *
     * @param state 状态
     * @return
     */
    public List<RideDemand> getRideDemandsByState(int state);

    /**
     * 根据用户号和路线Id 来获取需求
     * @param userNo
     * @param routeId
     * @return
     */
    public RideDemand  getDemandByUserNoAndRouteId(String userNo,Integer routeId);
}
