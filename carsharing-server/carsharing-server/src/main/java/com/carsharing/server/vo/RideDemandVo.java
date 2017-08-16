package com.carsharing.server.vo;

import com.carsharing.server.entity.RideDemand;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class RideDemandVo implements Serializable {

    private Integer matchDegree;

    private Integer demandId;

    private String userNo;

    private String userName;

    private String mobile;

    private Integer sex;

    private Integer routeId;

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

    private Integer riderCount;

    private Integer waitTime;

    private Integer rewards;

    private Integer state;

    private String describe;

    public Integer getMatchDegree() {
        return matchDegree;
    }

    public void setMatchDegree(Integer matchDegree) {
        this.matchDegree = matchDegree;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
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

    public Integer getRiderCount() {
        return riderCount;
    }

    public void setRiderCount(Integer riderCount) {
        this.riderCount = riderCount;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
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
        userName = demand.getUserName();
        sex = demand.getSex();
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
        isHome = demand.getIsHome();
        riderCount = demand.getRiderCount();
        waitTime = demand.getWaitTime();
        rewards = demand.getRewards();
        state = demand.getState();
        describe = demand.getDescribe();
    }
}
