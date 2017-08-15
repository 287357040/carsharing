package com.carsharing.server.common;

import com.carsharing.server.entity.RideRoute;
import com.carsharing.server.mapper.RideDemandMapper;
import com.carsharing.server.mapper.RideRouteMapper;
import com.carsharing.server.service.RideRouteService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class ScheduleTask {

    public final static long ONE_Minute =  60 * 1000;
    private Logger lo = Logger.getLogger(ScheduleTask.class);

    @Resource
    private RideDemandMapper rideDemandMapper;

    @Resource
    private RideRouteMapper rideRouteMapper;

    @Scheduled(cron = "1 0 0 * * ?")
    public void clearOverdueData() {
        try {
            Date date = new Date();
            rideDemandMapper.deleteOverdueDemands(date);
            rideRouteMapper.deleteOverdueRoutes(date);
        }catch (Exception e){
            lo.error("清理数据失败："+e.toString());
        }
    }
}
