package com.carsharing.server.vo;

import com.carsharing.server.entity.Driver;
import com.carsharing.server.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hucl on 2017/7/22.
 */
public class UserVo implements Serializable {

    private User user;

    private Driver driver;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver deriver) {
        this.driver = deriver;
    }

    public UserVo(User user , Driver driver){
        this.user = user;
        this.driver = driver;
    }

}
