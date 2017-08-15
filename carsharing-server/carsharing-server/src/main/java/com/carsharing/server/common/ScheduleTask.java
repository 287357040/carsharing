package com.carsharing.server.common;

import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.mapper.RideDemandMapper;
import com.carsharing.server.mapper.RideRouteMapper;
import com.carsharing.server.service.RideRouteService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;


public class ScheduleTask {

    private Logger lo = Logger.getLogger(ScheduleTask.class);
    @Resource
    private RideDemandMapper rideDemandMapper;

    @Resource
    private RideRouteMapper rideRouteMapper;

    @Scheduled(cron = "01 00 00 * * ?")
    public void clearOverdueData(Date date) {
        try {
            rideDemandMapper.deleteOverdueDemands(date);
            rideRouteMapper.deleteOverdueRoutes(date);
        }catch (Exception e){
            lo.error("清理数据失败："+e.toString());
        }
    }
}
