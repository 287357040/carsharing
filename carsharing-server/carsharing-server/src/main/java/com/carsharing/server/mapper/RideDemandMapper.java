package com.carsharing.server.mapper;

import com.carsharing.server.entity.RideDemand;
import com.carsharing.server.entity.RideRoute;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by hucl on 2017/7/18.
 */
public interface RideDemandMapper extends BaseDao<RideDemand> {

    public List<RideDemand> getRideDemandsByUserNo(@Param("userNo") String userNo, @Param("state") int state);

    public List<RideDemand> getRideDemandsByRouteId(int routeId);

    public List<RideDemand> getRideDemandsByState(int state);

    public RideDemand getDemandByUserNoAndRouteId(String userNo, Integer routeId);

    public Integer deleteOverdueDemands(Date date);
}
