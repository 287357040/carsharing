package com.carsharing.server.vo;

import com.carsharing.server.entity.RideDemand;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class RideDemandVo implements Serializable {

    private int matchDegree;

    private int demandId;

    private String userNo;

    private int routeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    private String startProvince;

    private String endProvince;

    private String startCity;

    private String endCity;

    private String startArea;

    private String endArea;

    private String startPlace;

    private String endPlace;

    private double startLongitude;

    private double endLongitude;

    private double startLatitude;

    private double endLatitude;

    private boolean isHome;

    private int riderCount;

    private int waitTime;

    private int rewards;

    private int state;

    private String describe;

    public int getMatchDegree() {
        return matchDegree;
    }

    public void setMatchDegree(int matchDegree) {
        this.matchDegree = matchDegree;
    }

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartProvince() {
        return startProvince;
    }

    public void setStartProvince(String startProvince) {
        this.startProvince = startProvince;
    }

    public String getEndProvince() {
        return endProvince;
    }

    public void setEndProvince(String endProvince) {
        this.endProvince = endProvince;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getEndArea() {
        return endArea;
    }

    public void setEndArea(String endArea) {
        this.endArea = endArea;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public double getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(double endLongitude) {
        this.endLongitude = endLongitude;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public boolean isHome() {
        return isHome;
    }

    public void setHome(boolean home) {
        isHome = home;
    }

    public int getRiderCount() {
        return riderCount;
    }

    public void setRiderCount(int riderCount) {
        this.riderCount = riderCount;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getRewards() {
        return rewards;
    }

    public void setRewards(int rewards) {
        this.rewards = rewards;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public RideDemandVo(RideDemand demand) {
        demandId = demand.getDemandId();
        userNo = demand.getUserNo();
        routeId = demand.getRouteId();
        startTime = demand.getStartTime();
        startArea = demand.getStartArea();
        endArea = demand.getEndArea();
        startPlace = demand.getStartPlace();
        endPlace = demand.getEndPlace();
        startLongitude = demand.getStartLongitude();
        endLongitude = demand.getEndLongitude();
        startLatitude = demand.getStartLatitude();
        endLatitude = demand.getEndLatitude();
        isHome = demand.isHome();
        riderCount = demand.getRiderCount();
        waitTime = demand.getWaitTime();
        rewards = demand.getRewards();
        state = demand.getState();
        describe = demand.getDescribe();
    }
}
