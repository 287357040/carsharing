package com.carsharing.server.entity;

import java.util.Date;

/**
 * Created by hucl on 2017/7/12.
 * 司机信息实体
 */
public class Driver {
    private String user_no;
    private String user_name;
    private String region;
    private String car_license;
    private String user_license;
    private Date license_date;
    private Date valid_date;
    private String car_no;
    private String color;
    private String brand;
    private String model;
    public void setUser_no(String user_no){
        this.user_no=user_no;
    }
    public String getUser_no(){
        return user_no;
    }
    public void setUser_name(String user_name){
        this.user_name=user_name;
    }
    public String getUser_name(){
        return user_name;
    }
    public void setRegion(String region){
        this.region=region;
    }
    public String getRegion(){
        return region;
    }
    public void setCar_license(String car_license){
        this.car_license=car_license;
    }
    public String getCar_license(){
        return car_license;
    }
    public void setUser_license(String user_license){
        this.user_license=user_license;
    }
    public String getUser_license(){
        return user_license;
    }
    public void setLicense_date(Date license_date){
        this.license_date=license_date;
    }
    public Date getLicense_date(){
        return license_date;
    }
    public void setValid_date(Date valid_date){
        this.valid_date=valid_date;
    }
    public Date getValid_date(){
        return valid_date;
    }
    public void setCar_no(String car_no){
        this.car_no=car_no;
    }
    public String getCar_no(){
        return car_no;
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
