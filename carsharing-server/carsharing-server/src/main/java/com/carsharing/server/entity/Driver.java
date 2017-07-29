package com.carsharing.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hucl on 2017/7/12.
 * 司机信息实体
 */

public class Driver implements Serializable {
    private String userNo;
    private String userName;
    private String region;
    private String carLicense;
    private String userLicense;
    private Date licenseDate;
    private Date validDate;
    private String carNo;
    private String color;
    private String brand;
    private String model;
    public void setUserNo(String userNo){
        this.userNo=userNo;
    }
    public String getUserNo(){
        return userNo;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setRegion(String region){
        this.region=region;
    }
    public String getRegion(){
        return region;
    }
    public void setCarLicense(String carLicense){
        this.carLicense=carLicense;
    }
    public String getCarLicense(){
        return carLicense;
    }
    public void setUserLicense(String userLicense){
        this.userLicense=userLicense;
    }
    public String getUserLicense(){
        return userLicense;
    }
    public void setLicenseDate(Date licenseDate){
        this.licenseDate=licenseDate;
    }
    public Date getLicenseDate(){
        return licenseDate;
    }
    public void setValidDate(Date validDate){
        this.validDate=validDate;
    }
    public Date getValidDate(){
        return validDate;
    }
    public void setCarNo(String carNo){
        this.carNo=carNo;
    }
    public String getCarNo(){
        return carNo;
    }
    public void setColor(String color){
        this.color=color;
    }
    public String getColor(){
        return color;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
    public String getBrand(){
        return brand;
    }
    public void setModel(String model){
        this.model=model;
    }
    public String getModel(){
        return model;
    }
}
